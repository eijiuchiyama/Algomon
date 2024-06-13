package com.algomon.game.screen

import com.algomon.game.Main
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen

class BuyMovementMenu(var game: Main):  KtxScreen{

    private val screenWidth = 640F
    private val screenHeight = 480F
    private val buttonWidth = 100F
    private val buttonHeight = 50F
    private val screenMovementsWidth = 600F
    private val screenMovementsHeight = 350F

    private val upTexture = Texture("assets/interval/up.png")
    private val downTexture = Texture("assets/interval/down.png")
    private val backTexture = Texture("assets/interval/back.png")
    private val screenTexture = Texture("assets/interval/screen.png")

    override fun render(delta: Float) {
        ScreenUtils.clear(0.9F, 0.5F, 0.5F, 1F)

        game.batch?.begin()

        if(Gdx.input.getX().toFloat() > screenWidth-buttonWidth-20F && Gdx.input.getX().toFloat() < screenWidth-20F && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão back
            if(Gdx.input.justTouched()){
                this.dispose()
                game.addScreen(IntervalMenu(game))
                game.setScreen<IntervalMenu>()
            }
        }

        game.batch?.draw(upTexture, 20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(downTexture, 20F+buttonWidth+20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(backTexture, screenWidth-buttonWidth-20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(screenTexture, 20F, 20F)

        game.batch?.end()
    }

}
