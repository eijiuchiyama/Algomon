package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.screen.GameScreen.Companion.log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import com.sun.media.sound.EmergencySoundbank.toFloat
import ktx.app.KtxGame
import ktx.app.KtxScreen


class StartScreen(var game: Main) : KtxScreen {

    var ortographicCamera = OrthographicCamera()

    private val buttonWidth: Float = 200F
    private val buttonHeight = 70
    private val exitWidth: Float = 50F
    private val exitHeight = 50

    private val logoTexture = Texture("assets/startScreen/logo.png")
    private val buttonTexture = Texture("assets/startScreen/button.png")
    private val buttonHoverTexture = Texture("assets/startScreen/buttonHover.png")
    private val exitTexture = Texture("assets/startScreen/exit.png")
    private val exitHoverTexture = Texture("assets/startScreen/exitHover.png")

    init {
        ortographicCamera.setToOrtho(false, 640F, 480F)
    }

    override fun show() {
        log.debug { "StartScreen gets shown" }

        super.show()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.35F, 0.7F, 0.25F, 1F);

        ortographicCamera.update();
        game.batch?.setProjectionMatrix(ortographicCamera.combined);

        game.batch?.begin()
        game.batch?.draw(logoTexture, 170F, 200F)
        if(Gdx.input.getX().toFloat() in 640/2-buttonWidth/2..640/2 + buttonWidth/2 &&
            480F - Gdx.input.getY().toFloat() in 100F..100F + buttonHeight){
            game.batch?.draw(buttonHoverTexture, 640/2 - buttonWidth/2, 100F)
        } else{
            game.batch?.draw(buttonTexture, 640/2 - buttonWidth/2, 100F)
        }
        if(Gdx.input.getX().toFloat() in 640-30-exitWidth..640F-30F &&
            480F - Gdx.input.getY().toFloat() in 30F..30F + exitHeight){
            game.batch?.draw(exitHoverTexture, 640-30-exitWidth, 30F)
        } else{
            game.batch?.draw(exitTexture, 640-30-exitWidth, 30F)
        }

        game.font?.draw(game.batch, "Criado por Fernando Yang e Lucas Eiji Uchiyama.", 5F, 20F)

        game.batch?.end()

    }
}
