package com.algomon.game

import com.algomon.game.screen.GameScreen
import com.algomon.game.screen.MenuScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import ktx.app.KtxGame
import ktx.app.KtxScreen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Null

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : ApplicationAdapter() {

    var batch: SpriteBatch
    var img: Texture = Texture("../screen/startbutton.png")

    override fun create() {
        //Gdx.app.logLevel = Application.LOG_DEBUG
        //addScreen(MenuScreen(this))
        //setScreen<GameScreen>()
        batch = SpriteBatch()
        img = Texture("../screen/startbutton.png")
    }

    override fun render() {
        //super.render()
        Gdx.gl.glClearColor(0F,0F,0F,0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(img, 50F, 0F)
        batch.end()
    }

}
