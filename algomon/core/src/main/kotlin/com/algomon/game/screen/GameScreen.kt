package com.algomon.game.screen

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationType
import com.algomon.game.component.ImageComponent
import com.algomon.game.component.ImageComponent.Companion.ImageComponentListener
import com.algomon.game.system.AnimationSystem
import com.algomon.game.system.RenderSystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.World
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.log.logger

class GameScreen : KtxScreen {
    private val stage: Stage = Stage(ExtendViewport(16f,9f))
    private val textureAtlas = TextureAtlas("assets/graphic/gameObject.atlas")

    private val world: World = World{
        inject(stage)

        componentListener<ImageComponentListener>()

        system<AnimationSystem>()
        system<RenderSystem>()
    }
    override fun show() {
        log.debug { "GameScreen gets shown" }

        world.entity {
            add<ImageComponent> {
                image = Image(TextureRegion(textureAtlas.findRegion("player"),0,0,48,48)).apply {
                    setSize(4f,4f)
                }
            }
            add<AnimationComponent> {
                nextAnimation("player", AnimationType.idleFront)
            }
        }
        world.entity {
            add<ImageComponent> {
                image = Image(TextureRegion(textureAtlas.findRegion("slime"),0,0,32,32)).apply {
                    setSize(6f,6f)
                    setPosition(10f,6f)
                    rotation = 90f
                }
            }
        }
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
    }
    companion object {
        private val log = logger<GameScreen>()
    }
}
