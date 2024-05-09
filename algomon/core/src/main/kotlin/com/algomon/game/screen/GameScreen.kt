package com.algomon.game.screen

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationModel
import com.algomon.game.component.AnimationType
import com.algomon.game.component.ImageComponent
import com.algomon.game.component.ImageComponent.Companion.ImageComponentListener
import com.algomon.game.event.MapChangeEvent
import com.algomon.game.event.fire
import com.algomon.game.system.AnimationSystem
import com.algomon.game.system.EntitySpawnSystem
import com.algomon.game.system.RenderSystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.World
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.log.logger

class GameScreen : KtxScreen {
    private val stage: Stage = Stage(ExtendViewport(21f,16f))
    private val textureAtlas = TextureAtlas("assets/graphic/gameObject.atlas")
    private var currentMap: TiledMap? = null

    private val world: World = World{
        inject(stage)
        inject(textureAtlas)

        componentListener<ImageComponentListener>()

        system<EntitySpawnSystem>()
        system<AnimationSystem>()
        system<RenderSystem>()
    }
    override fun show() {
        log.debug { "GameScreen gets shown" }

        world.systems.forEach { system ->
            if (system is EventListener){
                stage.addListener(system)
            }
        }
        currentMap = TmxMapLoader().load("assets/map/map.tmx")
        stage.fire(MapChangeEvent(currentMap!!))
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width,height,true)
    }
    override fun render(delta: Float) {
        world.update(delta)
    }

    override fun dispose() {
        stage.disposeSafely()
        textureAtlas.disposeSafely()
        world.dispose()
        currentMap?.disposeSafely()
    }
    companion object {
        private val log = logger<GameScreen>()
    }
}
