package com.algomon.game.screen

import com.algomon.game.component.ImageComponent.Companion.ImageComponentListener
import com.algomon.game.component.PhysicComponent
import com.algomon.game.component.PhysicComponent.Companion.PhysicComponentListener
import com.algomon.game.event.MapChangeEvent
import com.algomon.game.event.fire
import com.algomon.game.input.PlayerKeyboardInputProcessor
import com.algomon.game.system.AnimationSystem
import com.algomon.game.system.CameraSystem
import com.algomon.game.system.CollisionDespawnSystem
import com.algomon.game.system.CollisionSpawnSystem
import com.algomon.game.system.DebugSystem
import com.algomon.game.system.EntitySpawnSystem
import com.algomon.game.system.MoveSystem
import com.algomon.game.system.PhysicSystem
import com.algomon.game.system.RenderSystem
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.World
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.box2d.createWorld
import ktx.log.logger
import ktx.math.vec2

class GameScreen : KtxScreen {
    private val stage: Stage = Stage(ExtendViewport(17f,13f))
    private val textureAtlas = TextureAtlas("assets/graphic/gameObject.atlas")
    private var currentMap: TiledMap? = null
    private val phWorld = createWorld(gravity = vec2(0f,0f)).apply {
        autoClearForces = false
    }

    private val eworld: World = World{
        inject(stage)
        inject(textureAtlas)
        inject(phWorld)

        componentListener<ImageComponentListener>()
        componentListener<PhysicComponentListener>()

        system<EntitySpawnSystem>()
        system<CollisionSpawnSystem>()
        system<CollisionDespawnSystem>()
        system<MoveSystem>()
        system<PhysicSystem>()
        system<AnimationSystem>()
        system<CameraSystem>()
        system<RenderSystem>()
        //system<DebugSystem>()
    }
    override fun show() {
        log.debug { "GameScreen gets shown" }

        eworld.systems.forEach { system ->
            if (system is EventListener){
                stage.addListener(system)
            }
        }
        currentMap = TmxMapLoader().load("assets/map/map.tmx")
        stage.fire(MapChangeEvent(currentMap!!))

        PlayerKeyboardInputProcessor(eworld, eworld.mapper())
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width,height,true)
    }
    override fun render(delta: Float) {
        eworld.update(delta.coerceAtMost(0.25f))
    }

    override fun dispose() {
        stage.disposeSafely()
        textureAtlas.disposeSafely()
        eworld.dispose()
        currentMap?.disposeSafely()
        phWorld.disposeSafely()
    }
    companion object {
        private val log = logger<GameScreen>()
    }
}
