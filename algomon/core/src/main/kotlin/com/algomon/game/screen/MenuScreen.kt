package com.algomon.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import ktx.app.KtxGame
import ktx.app.KtxScreen

class MenuScreen(var game: KtxGame<KtxScreen>): KtxScreen {
    var playButtonActive: Texture = Texture("startbuttonhover.png")
    var playButtonInactive: Texture = Texture("startbutton.png")

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0F,0F,0F,0F)
        //game.batch
        //game.batch.draw(playButtonInactive, 100, 100)
        //game.batch.end()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }

}
