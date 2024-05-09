package com.algomon.game.system

import com.algomon.game.Main.Companion.UNIT_SCALE
import com.algomon.game.component.ImageComponent
import com.algomon.game.event.MapChangeEvent
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.collection.compareEntity
import ktx.assets.disposeSafely
import ktx.graphics.use
import ktx.tiled.forEachLayer
import java.security.PrivateKey

@AllOf([ImageComponent::class])
class RenderSystem(
    private val stage: Stage,
    private val imageCmps: ComponentMapper<ImageComponent>
): EventListener, IteratingSystem(
    comparator = compareEntity{ e1, e2 -> imageCmps[e1].compareTo(imageCmps[e2]) }
) {

    private val bgdlayers = mutableListOf<TiledMapTileLayer>()
    private val fgdlayers = mutableListOf<TiledMapTileLayer>()
    private val mapRenderer = OrthogonalTiledMapRenderer(null, UNIT_SCALE, stage.batch)
    private val orthoCam = stage.camera as OrthographicCamera
    override fun onTick() {
        super.onTick()

        with(stage){
            viewport.apply()

            AnimatedTiledMapTile.updateAnimationBaseTime()
            mapRenderer.setView(orthoCam)

            if (bgdlayers.isNotEmpty()){
                stage.batch.use(orthoCam.combined) {
                    bgdlayers.forEach { mapRenderer.renderTileLayer(it) }
                }
            }

            act(deltaTime)
            draw()

            if (fgdlayers.isNotEmpty()){
                stage.batch.use(orthoCam.combined) {
                    fgdlayers.forEach { mapRenderer.renderTileLayer(it) }
                }
            }
        }
    }
    override fun onTickEntity(entity: Entity) {
        imageCmps[entity].image.toFront()
    }

    override fun handle(event: Event?): Boolean {
        when(event){
            is MapChangeEvent -> {
                bgdlayers.clear()
                fgdlayers.clear()

                event.map.forEachLayer<TiledMapTileLayer> {layer ->
                    if(layer.name.startsWith("fgd_")){
                        fgdlayers.add(layer)
                    } else {
                        bgdlayers.add(layer)
                    }
                }
                return true
            }
        }

        return false
    }

    override fun onDispose() {
        mapRenderer.disposeSafely()
    }
}
