package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.Movement
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
    private val boxTexture = Texture("assets/battle/box.png")
    private val moveBox = Texture("assets/battle/moveBox.png")

    private var i = 0

    override fun render(delta: Float) {
        ScreenUtils.clear(0.9F, 0.5F, 0.5F, 1F)

        game.batch?.begin()

        printCarteira(100)

        if(Gdx.input.getX().toFloat() > screenWidth-buttonWidth-20F && Gdx.input.getX().toFloat() < screenWidth-20F && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão back
            if(Gdx.input.justTouched()){
                this.dispose()
                if(!game.containsScreen<IntervalMenu>())
                    game.addScreen(IntervalMenu(game))
                game.setScreen<IntervalMenu>()
            }
        }

        if(Gdx.input.getX().toFloat() > 20F && Gdx.input.getX().toFloat() < 20F+buttonWidth && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão up
            if(Gdx.input.justTouched()){
                i--
            }
        }

        if(Gdx.input.getX().toFloat() > 20F+buttonWidth+20F && Gdx.input.getX().toFloat() < 20F+2*buttonWidth+20F && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão down
            if(Gdx.input.justTouched()){
                i++
            }
        }

        game.batch?.draw(upTexture, 20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(downTexture, 20F+buttonWidth+20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(backTexture, screenWidth-buttonWidth-20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(screenTexture, 20F, 20F)

        game.batch?.end()
    }

    fun showMovements(movimentos: List<Movement>){

    }

    fun removeMovement(){
        game.batch?.draw(boxTexture, 0F, 0F)
    }

    fun showMovements(){
        game.batch?.draw(moveBox, 0F, 0F)
    }

    fun printCarteira(carteira: Int){
        game.font18?.draw(game.batch, "Carteira: $${carteira}", 20F+2*buttonWidth+40F , screenHeight-40F-20F,
            screenWidth-3*buttonWidth-5*20F, 1, true)
    }

}
