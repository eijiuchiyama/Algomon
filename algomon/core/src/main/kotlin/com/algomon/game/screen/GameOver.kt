package com.algomon.game.screen

import com.algomon.game.Main
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen

class GameOver(var game: Main): KtxScreen{

    private val screenTexture = Texture("assets/intro/screen.png")

    private val screenTextureWidth = 600F
    private val screenTextureHeight = 350F
    private val screenWidth = 640F
    private val screenHeight = 480F

    private val quitWidth = 100F
    private val quitHeight = 50F
    private val tryAgainWidth = 150F
    private val tryAgainHeight = 50F

    private val tryAgainTexture = Texture("assets/ending/tryAgain.png")
    private val quitTexture = Texture("assets/ending/quit.png")

    private val soundEffect = Gdx.audio.newSound(Gdx.files.internal("assets/music/buttonSound.mp3"))

    override fun render(delta: Float) {
        ScreenUtils.clear(0.2F, 0.2F, 0.2F, 1F)

        game.batch?.begin()

        game.batch?.draw(screenTexture, screenWidth/2 - screenTextureWidth/2, 100F)
        game.fontGameOver?.draw(game.batch, "Game Over", 50F,
            300F, screenWidth-100F, 1, true)

        game.batch?.draw(quitTexture, screenWidth-quitWidth-30, 30F)
        game.batch?.draw(tryAgainTexture, 30F, 30F)

        if(Gdx.input.getX().toFloat() in 30F..30F + tryAgainWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + tryAgainHeight){
            if(Gdx.input.justTouched()){
                soundEffect.play()
                this.dispose()
                if(!game.containsScreen<StartScreen>())
                    game.addScreen(StartScreen(game))
                game.setScreen<StartScreen>()

            }
        }

        if(Gdx.input.getX().toFloat() in screenWidth-30-quitWidth..screenWidth-30+tryAgainWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + screenHeight){
            if(Gdx.input.justTouched()){
                Gdx.app.exit()
            }
        }

        game.batch?.end()

    }
}
