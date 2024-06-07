package com.algomon.game

import com.algomon.game.screen.GameScreen
import com.algomon.game.screen.StartScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen


/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : KtxGame<KtxScreen>(){

    var batch: SpriteBatch? = null
    var font: BitmapFont? = null

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()

        Gdx.app.logLevel = Application.LOG_DEBUG
        addScreen(StartScreen(this))
        setScreen<StartScreen>()
    }

    companion object{
        const val UNIT_SCALE = 1 / 16f
    }
}
