package com.algomon.game

import com.algomon.game.screen.Battle
import com.algomon.game.screen.GameOver
import com.algomon.game.screen.GameScreen
import com.algomon.game.screen.YouWin
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
    var fontGameOver: BitmapFont? = null
    var fontYouWin: BitmapFont? = null

    override fun create() {
        batch = SpriteBatch()
        val generator = FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/pixelOperator/PixelOperator8.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 18
        parameter.color = Color.BLACK
        font18 = generator.generateFont(parameter) // font size 12 pixels
        parameter.size = 12
        font12 = generator.generateFont(parameter)
        parameter.size = 40
        parameter.color = Color.RED
        fontGameOver = generator.generateFont(parameter)
        parameter.color = Color.GOLDENROD
        fontYouWin = generator.generateFont(parameter)

        generator.dispose()

        Gdx.app.logLevel = Application.LOG_DEBUG
        //addScreen(YouWin(this))
        //addScreen(StartScreen(this))
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    companion object{
        const val UNIT_SCALE = 1 / 16f
    }
}
