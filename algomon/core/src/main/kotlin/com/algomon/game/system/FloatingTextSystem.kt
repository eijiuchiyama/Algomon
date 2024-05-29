package com.algomon.game.system

import com.algomon.game.component.FloatingTextComponent
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.Qualifier
import ktx.math.vec2

@AllOf([FloatingTextComponent::class])
class FloatingTextSystem(
    private val gameStage: Stage,
    @Qualifier("uiStage") private val uiStage: Stage,
    private val textCmps: ComponentMapper<FloatingTextComponent>
) : IteratingSystem() {
    private val uiLocation = vec2()
    private val uiTarger = vec2()

    private fun Vector2.toUiCoordinates(from: Vector2){
        this.set(from)
        gameStage.viewport.project(this)
        uiStage.viewport.unproject(this)
    }
    override fun onTickEntity(entity: Entity) {
        with(textCmps[entity]){
            if (time >= lifeSpan){
                world.remove(entity)
                return
            }

            time += deltaTime
            uiLocation.toUiCoordinates(txtLocation)
            uiTarger.toUiCoordinates(txtLocation)

            uiLocation.interpolate(uiTarger, (time/lifeSpan).coerceAtMost(1f), Interpolation.smooth)
            label.setPosition(uiLocation.x, uiStage.viewport.worldHeight - uiLocation.y)
        }
    }
}
