package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.Movement
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen

class IntervalMenu(var game: Main): KtxScreen {

    private val screenWidth = 640F
    private val screenHeight = 480F
    private val buttonWidth = 400F
    private val buttonHeight = 100F

    private val trainingTexture = Texture("assets/interval/training.png")
    private val learnMoveTexture = Texture("assets/interval/learnMove.png")
    private val nextBattleTexture = Texture("assets/interval/nextBattle.png")

    override fun render(delta: Float) {
        ScreenUtils.clear(0.9F, 0.5F, 0.5F, 1F)

        game.batch?.begin()

        if(Gdx.input.getX().toFloat() > screenWidth/2-buttonWidth/2 && Gdx.input.getX().toFloat() < screenWidth/2-buttonWidth/2+buttonWidth && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){
            if(Gdx.input.justTouched()){
                this.dispose()
                if(!game.containsScreen<Battle>())
                    game.addScreen(Battle(game))
                game.setScreen<Battle>()
            }
        }

        if(Gdx.input.getX().toFloat() > screenWidth/2-buttonWidth/2 && Gdx.input.getX().toFloat() < screenWidth/2-buttonWidth/2+buttonWidth && screenHeight - Gdx.input.getY().toFloat() > screenHeight-2*buttonHeight-80F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-buttonHeight-80F){
            if(Gdx.input.justTouched()){
                this.dispose()
                if(!game.containsScreen<BuyMovementMenu>())
                    game.addScreen(BuyMovementMenu(game))
                game.setScreen<BuyMovementMenu>()
            }
        }

        if(Gdx.input.getX().toFloat() > screenWidth/2-buttonWidth/2 && Gdx.input.getX().toFloat() < screenWidth/2-buttonWidth/2+buttonWidth && screenHeight - Gdx.input.getY().toFloat() > screenHeight-3*buttonHeight-120F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-2*buttonHeight-120F){
            if(Gdx.input.justTouched()){
                this.dispose()
                if(!game.containsScreen<Battle>())
                    game.addScreen(Battle(game))
                game.setScreen<Battle>()
            }
        }

        game.batch?.draw(trainingTexture, screenWidth/2-buttonWidth/2, screenHeight-buttonHeight-40F)
        game.batch?.draw(learnMoveTexture, screenWidth/2-buttonWidth/2, screenHeight-2*buttonHeight-80F)
        game.batch?.draw(nextBattleTexture, screenWidth/2-buttonWidth/2, screenHeight-3*buttonHeight-120F)

        game.batch?.end()

    }

}
