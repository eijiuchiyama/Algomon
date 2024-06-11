package com.algomon.game.system

import com.algomon.game.Main
import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationModel
import com.algomon.game.component.FloatingTextComponent
import com.algomon.game.component.InteractableComponent
import com.algomon.game.component.PhysicComponent
import com.algomon.game.screen.Battle
import com.algomon.game.screen.StartScreen
import com.algomon.game.system.EntitySpawnSystem.Companion.HIT_BOX_SENSOR
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.Qualifier
import com.github.quillraven.fleks.WorldConfiguration
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.box2d.box

@AllOf([InteractableComponent::class])
class InteractableSystem(
    private val gameMain: Main,
    private val intableCmps: ComponentMapper<InteractableComponent>,
    private val aniCmps: ComponentMapper<AnimationComponent>,
    private val physicCmps: ComponentMapper<PhysicComponent>
) : IteratingSystem() {
    private val textFont = BitmapFont(Gdx.files.internal("assets/textFont/textFont.fnt"))
    private val floatingTextStyle = LabelStyle(textFont ,Color.WHITE)
    override fun onTickEntity(entity: Entity) {
        with(intableCmps[entity]){
            val aniCmp = aniCmps[entity]
            if (interactEntity == null){
                return
            }

            if (aniCmp.model == AnimationModel.door){
                interactEntity = null
                val physicCmp = physicCmps[entity]
                floatingText("Boom", physicCmp.body.position, physicCmp.size)
                if (physicCmp.body.userData != HIT_BOX_SENSOR){
                    world.remove(entity)
                }
                //configureEntity(entity){ physicCmps.remove(it) }
                /*aniCmp.nextAnimation(AnimationType.open)
                aniCmp.playMode = Animation.PlayMode.NORMAL*/
            }

            if (aniCmp.model == AnimationModel.computer){
                interactEntity = null
                val physicCmp = physicCmps[entity]
                floatingText("Click", physicCmp.body.position, physicCmp.size)
                gameMain.addScreen(Battle(gameMain))
                gameMain.setScreen<Battle>()
            }
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

    override fun onDispose() {
        textFont.disposeSafely()
    }
}
