package com.algomon.game.system

import com.algomon.game.component.ImageComponent
import com.algomon.game.component.PlayerComponent
import com.algomon.game.event.MapChangeEvent
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import ktx.tiled.height
import ktx.tiled.width

@AllOf([PlayerComponent::class, ImageComponent::class])
class CameraSystem(
    private val imageCmps:ComponentMapper<ImageComponent>,
    stage:Stage
):EventListener, IteratingSystem() {
    private val camera = stage.camera
    private var maxW = 0f
    private var maxH = 0f

    override fun onTickEntity(entity: Entity) {
        with(imageCmps[entity]){
            val viewW = camera.viewportWidth * 0.5f
            val viewH = camera.viewportHeight * 0.5f
            camera.position.set(
                image.x.coerceIn(viewW, maxW-viewW),
                image.y.coerceIn(viewH, maxH-viewH),
                camera.position.z
            )
        }
    }

    override fun handle(event: Event?): Boolean {
        if (event is MapChangeEvent){
            maxW = event.map.width.toFloat()
            maxH = event.map.height.toFloat()
            return true
        }
        return false
    }
}
