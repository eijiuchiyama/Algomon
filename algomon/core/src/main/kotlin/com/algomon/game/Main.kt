package com.algomon.game

import com.algomon.game.screen.Battle
import com.algomon.game.screen.StartScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ktx.app.KtxGame
import ktx.app.KtxScreen


/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : KtxGame<KtxScreen>(){

    var batch: SpriteBatch? = null
    var font18: BitmapFont? = null
    var font12: BitmapFont? = null

    override fun create() {
        batch = SpriteBatch()
        val generator = FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/pixelOperator/PixelOperator8.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 18
        parameter.color = Color.BLACK
        font18 = generator.generateFont(parameter) // font size 12 pixels
        parameter.size = 12
        font12 = generator.generateFont(parameter)

        generator.dispose()

        Gdx.app.logLevel = Application.LOG_DEBUG
        addScreen(StartScreen(this))
        setScreen<StartScreen>()
    }

    companion object{
        const val UNIT_SCALE = 1 / 16f
    }
}
