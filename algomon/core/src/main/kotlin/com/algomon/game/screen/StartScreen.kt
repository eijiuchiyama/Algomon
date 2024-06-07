package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.screen.GameScreen.Companion.log
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxGame
import ktx.app.KtxScreen


class StartScreen : KtxScreen {

    var game: Main? = null
    var ortographicCamera = OrthographicCamera()

    private val buttonTexture = Texture("assets/startScreen/button.png")

    fun StartScreen(game: Main) {
        this.game = game
        ortographicCamera.setToOrtho(false, 640F, 480F)
    }

    override fun show() {
        log.debug { "StartScreen gets shown" }

        super.show()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.2F, 0.2F, 0.2F, 1F);

        ortographicCamera.update();
        game?.batch?.setProjectionMatrix(ortographicCamera.combined);

        game?.batch?.begin()

        game?.batch?.draw(buttonTexture, 300F, 300F)

        game?.batch?.end()

    }
}
