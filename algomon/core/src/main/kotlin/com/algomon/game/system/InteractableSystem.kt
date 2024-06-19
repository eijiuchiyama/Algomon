package com.algomon.game.system

import com.algomon.game.Main
import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationModel
import com.algomon.game.component.AnimationType
import com.algomon.game.component.Direction
import com.algomon.game.component.FloatingTextComponent
import com.algomon.game.component.InteractableComponent
import com.algomon.game.component.MoveComponent
import com.algomon.game.component.PhysicComponent
import com.algomon.game.event.EntityOpenEvent
import com.algomon.game.event.MapChangeEvent
import com.algomon.game.event.ScreenChangeEvent
import com.algomon.game.event.fire
import com.algomon.game.screen.BuyMovementMenu
import com.algomon.game.screen.CommonBattle
import com.algomon.game.screen.SpecialBattle
import com.algomon.game.system.EntitySpawnSystem.Companion.HIT_BOX_SENSOR
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import ktx.assets.disposeSafely

@AllOf([InteractableComponent::class])
class InteractableSystem(
    private val gameMain: Main,
    private val interactableCmps: ComponentMapper<InteractableComponent>,
    private val aniCmps: ComponentMapper<AnimationComponent>,
    private val physicCmps: ComponentMapper<PhysicComponent>,
    private val moveCmps: ComponentMapper<MoveComponent>,
    private val stage: Stage,
) : IteratingSystem() {
    private val textFont = BitmapFont(Gdx.files.internal("assets/textFont/textFont.fnt")).apply { data.setScale(0.25f) }
    private val floatingTextStyle = LabelStyle(textFont ,Color.WHITE)
    override fun onTickEntity(entity: Entity) {
        with(interactableCmps[entity]){
            val aniCmp = aniCmps[entity]

            if (interactEntity == null){
                return
            }

            val intEntity = interactEntity
            var direction = Direction.NULL
            intEntity?.let {
                direction = moveCmps[intEntity].direction
            }

            if (aniCmp.model == AnimationModel.door && direction == Direction.BACK){
                interactEntity = null
                stage.fire(EntityOpenEvent(aniCmp.model))
                val physicCmp = physicCmps[entity]
                floatingText("Boom", physicCmp.body.position, physicCmp.size)
                if (physicCmp.body.userData != HIT_BOX_SENSOR){
                    configureEntity(entity){
                        physicCmps.remove(entity)
                    }
                    aniCmp.nextAnimation(AnimationType.open)
                    aniCmp.playMode = Animation.PlayMode.NORMAL
                }
            }

            if (aniCmp.model == AnimationModel.door2 && direction == Direction.BACK){
                interactEntity = null
                stage.fire(EntityOpenEvent(aniCmp.model))
                val physicCmp = physicCmps[entity]
                floatingText("Boom", physicCmp.body.position, physicCmp.size)
                if (physicCmp.body.userData != HIT_BOX_SENSOR){
                    configureEntity(entity){
                        physicCmps.remove(entity)
                    }
                    aniCmp.nextAnimation(AnimationType.open)
                    aniCmp.playMode = Animation.PlayMode.NORMAL
                }
                switchMap("assets/map/map.tmx")
            }

            if (aniCmp.model == AnimationModel.exit && direction == Direction.FRONT){
                interactEntity = null
                switchMap("assets/map/room.tmx")
            }

            if (aniCmp.model == AnimationModel.computer && direction == Direction.FRONT){
                interactEntity = null
                stage.fire(EntityOpenEvent(aniCmp.model))
                val physicCmp = physicCmps[entity]
                floatingText("Click", physicCmp.body.position, physicCmp.size)
                if(!gameMain.containsScreen<SpecialBattle>())
                    gameMain.addScreen(SpecialBattle(gameMain))
                gameMain.setScreen<SpecialBattle>()
                stage.fire(ScreenChangeEvent("music/Battle_BGM.mp3"))
            }

            if (aniCmp.model == AnimationModel.computer2 && direction == Direction.FRONT){
                interactEntity = null
                stage.fire(EntityOpenEvent(aniCmp.model))
                val physicCmp = physicCmps[entity]
                floatingText("Click", physicCmp.body.position, physicCmp.size)
                if(!gameMain.containsScreen<CommonBattle>())
                    gameMain.addScreen(CommonBattle(gameMain))
                gameMain.setScreen<CommonBattle>()
                stage.fire(ScreenChangeEvent("music/Train_BGM.mp3"))
            }

            if (aniCmp.model == AnimationModel.shelf && (direction == Direction.FRONT || direction == Direction.BACK)){
                interactEntity = null
                stage.fire(EntityOpenEvent(aniCmp.model))
                val physicCmp = physicCmps[entity]
                floatingText("Shuuaa", physicCmp.body.position, physicCmp.size)
                if(!gameMain.containsScreen<BuyMovementMenu>())
                    gameMain.addScreen(BuyMovementMenu(gameMain))
                gameMain.setScreen<BuyMovementMenu>()
                stage.fire(ScreenChangeEvent("music/Battle_BGM.mp3"))
            }

            interactEntity = null
        }
    }

    private fun floatingText(text: String, position: Vector2, size: Vector2){
        world.entity{
            add<FloatingTextComponent>{
                txtLocation.set(position.x - size.x * 0.5f, position.y)
                lifeSpan = 1.5f
                label = Label(text, floatingTextStyle)
            }
        }
    }

    fun switchMap(path: String) {
        val runnable = Runnable {
            val currentMap = TmxMapLoader().load(path)
            stage.fire(MapChangeEvent(currentMap!!))
        }
        stage.root.color.a = 1f
        val sequenceAction = SequenceAction().apply {
            addAction(Actions.fadeOut(0.5f))
            addAction(Actions.run(runnable))
            addAction(Actions.fadeIn(0.5f))
        }
        stage.root.addAction(sequenceAction)
    }

    override fun onDispose() {
        textFont.disposeSafely()
    }
}
