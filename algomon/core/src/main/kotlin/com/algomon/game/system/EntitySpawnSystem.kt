package com.algomon.game.system

import com.algomon.game.Main.Companion.UNIT_SCALE
import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationModel
import com.algomon.game.component.AnimationType
import com.algomon.game.component.DEFAULT_SPEED
import com.algomon.game.component.ImageComponent
import com.algomon.game.component.MoveComponent
import com.algomon.game.component.PhysicComponent
import com.algomon.game.component.PhysicComponent.Companion.physicCmpFromImage
import com.algomon.game.component.PlayerComponent
import com.algomon.game.component.SpawnCfg
import com.algomon.game.component.SpawnComponent
import com.algomon.game.event.MapChangeEvent
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import ktx.app.gdxError
import ktx.box2d.box
import ktx.math.vec2
import ktx.tiled.layer
import ktx.tiled.type
import ktx.tiled.x
import ktx.tiled.y

@AllOf([SpawnComponent::class])
class EntitySpawnSystem(
    private val phWorld: World,
    private val atlas: TextureAtlas,
    private val spawnCmps: ComponentMapper<SpawnComponent>
) : EventListener, IteratingSystem(){
    private val cacheCfgs = mutableMapOf<String, SpawnCfg>()
    private val cacheSize = mutableMapOf<AnimationModel, Vector2>()

    override fun onTickEntity(entity: Entity) {
        with(spawnCmps[entity]){
            val cfg = spawnCfg(type)
            val relativeSize = size(cfg.model)

            world.entity{
                val imageCmp = add<ImageComponent>{
                    image = Image().apply {
                        setPosition(location.x, location.y)
                        setSize(relativeSize.x, relativeSize.y)
                        setScaling(Scaling.fill)
                    }
                }

                add<AnimationComponent> {
                    nextAnimation(cfg.model, AnimationType.idleFront)
                }

                physicCmpFromImage(phWorld, imageCmp.image, cfg.bodyType){ phCmp, width, height ->
                    val w = width * cfg.physicScaling.x
                    val h = height * cfg.physicScaling.y

                    box(w, h, cfg.physicOffset){
                        isSensor = false
                    }
                }

                if (cfg.speedScaling > 0f) {
                    add<MoveComponent>{
                        speed = DEFAULT_SPEED * cfg.speedScaling
                    }
                }

                if (type == "Player"){
                    add<PlayerComponent>()
                }
            }
        }
        world.remove(entity)
    }

    private fun spawnCfg(type: String): SpawnCfg = cacheCfgs.getOrPut(type) {
        when(type){
            "Player" -> SpawnCfg(
                AnimationModel.player,
                physicScaling = vec2(0.3f, 0.3f),
                physicOffset = vec2(0f, -10f * UNIT_SCALE)
            )
            "Slime" -> SpawnCfg(
                AnimationModel.slime,
                physicScaling = vec2(0.3f, 0.3f),
                physicOffset = vec2(0f, -2f * UNIT_SCALE)
            )
            else -> gdxError("Type $type has no SpawnCfg setup")
        }
    }

    private fun size(model: AnimationModel) = cacheSize.getOrPut(model){
        val regions = atlas.findRegions("${model.atlasKey}/${AnimationType.idleFront.atlasKey}")
        if(regions.isEmpty){
            gdxError("There are no regions for the idleFront animation of model $model")
        }
        val firstFrame = regions.first()
        vec2(firstFrame.originalWidth * UNIT_SCALE, firstFrame.originalHeight * UNIT_SCALE)
    }

    override fun handle(event: Event?): Boolean {
        when(event){
            is MapChangeEvent -> {
                val entityLayer = event.map.layer("entities")
                entityLayer.objects.forEach { mapObj ->
                    val type = mapObj.type ?: gdxError("MapObject $mapObj does not have a type")
                    world.entity {
                        add<SpawnComponent> {
                            this.type = type
                            this.location.set(mapObj.x * UNIT_SCALE, mapObj.y * UNIT_SCALE)
                        }
                    }
                }
                return true
            }
        }
        return false
    }
}
