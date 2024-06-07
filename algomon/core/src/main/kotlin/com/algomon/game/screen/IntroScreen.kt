package com.algomon.game.screen

import com.algomon.game.Main
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen

class IntroScreen(var game: Main): KtxScreen {

    private val nextWidth = 100F
    private val nextHeight = 50
    private val skipAllWidth = 150F
    private val skipAllHeight = 50
    private val screenTextureWidth = 600F
    private val screenTextureHeight = 350F
    private val screenWidth = 640F
    private val screenHeight = 480F

    private val nextTexture = Texture("assets/intro/next.png")
    private val skipAllTexture = Texture("assets/intro/skipAll.png")
    private val screenTexture = Texture("assets/intro/screen.png")

    private val music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/bitBeats4.mp3"))

    init{
        music.play()
    }

    override fun show() {
        GameScreen.log.debug { "IntroScreen gets shown" }

        super.show()
    }

    override fun render(delta: Float) {

        ScreenUtils.clear(0.2F, 0.2F, 0.2F, 1F)

        if(!music.isPlaying){
            music.play()
        }

        game.batch?.begin()

        game.batch?.draw(screenTexture, screenWidth/2 - screenTextureWidth/2, 100F)
        game.batch?.draw(nextTexture, screenWidth-nextWidth-30, 30F)
        game.batch?.draw(skipAllTexture, 30F, 30F)

        if(Gdx.input.getX().toFloat() in 30F..30F + skipAllWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + skipAllHeight){
            if(Gdx.input.isTouched()){
                this.dispose()
                game.addScreen(GameScreen())
                game.setScreen<GameScreen>()
            }
        }

        if(Gdx.input.getX().toFloat() in screenWidth-30-nextWidth..screenWidth-30+skipAllWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + screenHeight){
            if(Gdx.input.isTouched()){

            }
        }

        game.batch?.end()

    }

    override fun dispose() {
        music.dispose()
    }
}
