package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.Movement
import com.algomon.game.enemy
import com.algomon.game.getMovement
import com.algomon.game.getPossibleMovementsId
import com.algomon.game.getPossibleMovementsName
import com.algomon.game.getPossibleMovementsPrice
import com.algomon.game.player
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen
import kotlin.math.ceil

class BuyMovementMenu(var game: Main):  KtxScreen{

    private var escolhido = false
    private var remove = false
    private var chosenMove = 0
    private var finalText = false

    private val screenWidth = 640F
    private val screenHeight = 480F
    private val buttonWidth = 100F
    private val buttonHeight = 50F
    private val screenMovementsWidth = 600F
    private val screenMovementsHeight = 350F
    private val boxWidth = 640F
    private val boxHeight = 120F
    private val movementsBoxWidth = 640F
    private val movementsBoxHeight = 200F

    private val upTexture = Texture("assets/interval/up.png")
    private val downTexture = Texture("assets/interval/down.png")
    private val backTexture = Texture("assets/interval/back.png")
    private val screenTexture = Texture("assets/interval/screen.png")
    private val boxTexture = Texture("assets/battle/box.png")
    private val moveBoxTexture = Texture("assets/battle/moveBox.png")

    private var i = 0

    override fun render(delta: Float) {
        ScreenUtils.clear(0.9F, 0.5F, 0.5F, 1F)

        game.batch?.begin()

        game.batch?.draw(upTexture, 20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(downTexture, 20F+buttonWidth+20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(backTexture, screenWidth-buttonWidth-20F, screenHeight-buttonHeight-40F)
        game.batch?.draw(screenTexture, 20F, 20F)

        printCarteira()

        if(Gdx.input.getX().toFloat() > screenWidth-buttonWidth-20F && Gdx.input.getX().toFloat() < screenWidth-20F && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão back
            if(Gdx.input.justTouched()){
                this.dispose()
                if(!game.containsScreen<GameScreen>()) {
                    game.addScreen(GameScreen(game))
                    game.setScreen<GameScreen>()
                }
                else game.setScreenExists(GameScreen(game))
            }
        }

        if(Gdx.input.getX().toFloat() > 20F && Gdx.input.getX().toFloat() < 20F+buttonWidth && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão up
            if(Gdx.input.justTouched()){
                if(i > 0)
                    i--
            }
        }

        if(Gdx.input.getX().toFloat() > 20F+buttonWidth+20F && Gdx.input.getX().toFloat() < 20F+2*buttonWidth+20F && screenHeight - Gdx.input.getY().toFloat() > screenHeight-buttonHeight-40F &&
            screenHeight - Gdx.input.getY().toFloat() < screenHeight-40F){ //Toca no botão down
            if(Gdx.input.justTouched()){
                if(i < ceil((getPossibleMovementsId(player).size / 6).toDouble()))
                    i++
            }
        }

        if(escolhido == false)
            showMovements()

        if(escolhido == true && remove == false)
            showText()

        if(remove == true)
            removeMovement()

        if(finalText == true){
            removed()
        }

        game.batch?.end()
    }

    fun removed(){
        game.batch?.draw(boxTexture, 0F, 0F)
        game.font18?.draw(game.batch, "Novo movimento instalado com sucesso", 20F, boxHeight-20F, boxWidth-40F, -1, true)
        if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
            screenHeight - Gdx.input.getY().toFloat() < boxHeight){
            if(Gdx.input.justTouched()){
                finalText = false
                escolhido = false
            }
        }
    }

    fun removeMovement(){
        game.batch?.draw(moveBoxTexture, 0F, 0F)
        var x = 0F
        var y = 0F
        for(i in player.skills){
            game.font12?.draw(game.batch, i.name, 10F+x, movementsBoxHeight-40-y, screenWidth/3-40, 1, true)
            if(x > screenWidth / 2) {
                x = 0F
                y += movementsBoxHeight/2
            } else {
                x += screenWidth / 3
            }
        }
        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth / 3 && screenHeight - Gdx.input.getY()
                .toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight) {
            if (Gdx.input.justTouched()) {
                println("Movimento 0 escolhido")
                player.skills.removeAt(0)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
        if (Gdx.input.getX().toFloat() > screenWidth / 3 && Gdx.input.getX().toFloat() < 2 * screenWidth / 3 && screenHeight - Gdx.input.getY()
                .toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight) {
            if (Gdx.input.justTouched()) {
                println("Movimento 1 escolhido")
                player.skills.removeAt(1)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
        if (Gdx.input.getX().toFloat() > 2*screenWidth / 3 && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY()
                .toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight) {
            if (Gdx.input.justTouched()) {
                println("Movimento 2 escolhido")
                player.skills.removeAt(2)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth / 3 && screenHeight - Gdx.input.getY()
                .toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight/2) {
            if (Gdx.input.justTouched()) {
                println("Movimento 3 escolhido")
                player.skills.removeAt(3)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
        if (Gdx.input.getX().toFloat() > screenWidth / 3 && Gdx.input.getX().toFloat() < 2*screenWidth / 3 && screenHeight - Gdx.input.getY()
                .toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight/2) {
            if (Gdx.input.justTouched()) {
                println("Movimento 4 escolhido")
                player.skills.removeAt(4)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
        if (Gdx.input.getX().toFloat() > 2*screenWidth / 3 && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY()
                .toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight/2) {
            if (Gdx.input.justTouched()) {
                println("Movimento 5 escolhido")
                player.skills.removeAt(5)
                var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
                player.skills.add(movement)
                player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
                remove = false
                finalText = true
            }
        }
    }

    fun showText(){
        game.batch?.draw(boxTexture, 0F, 0F)
        if(player.skills.size >= 6){
            game.font18?.draw(game.batch, "Você possui seis movimentos. Remova um para instalar o novo", 20F, boxHeight-20F, boxWidth-40F, -1, true)

            if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < boxHeight){
                if(Gdx.input.justTouched()){
                    remove = true
                }
            }
        } else{
            game.font18?.draw(game.batch, "Novo movimento instalado com sucesso", 20F, boxHeight-20F, boxWidth-40F, -1, true)
            var movement = getMovement(getPossibleMovementsId(player)[chosenMove])
            player.skills.add(movement)
            player.carteira -= getPossibleMovementsPrice(player)[chosenMove]
            if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < boxHeight){
                if(Gdx.input.justTouched()){
                    escolhido = false
                }
            }
        }
    }

    fun showMovements(){
        //game.batch?.draw(moveBox, 0F, 0F)
        val possibleMovements = getPossibleMovementsId(player)
        val possibleMovementsName = getPossibleMovementsName(player)
        val possibleMovementsPrice = getPossibleMovementsPrice(player)
        if(possibleMovements.size > i*6)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6]} $${possibleMovementsPrice[i*6]}", 60F, 30F+6*screenMovementsHeight/7)
        if(possibleMovements.size > i*6+1)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6+1]} $${possibleMovementsPrice[i*6+1]}", 60F, 30F+5*screenMovementsHeight/7)
        if(possibleMovements.size > i*6+2)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6+2]} $${possibleMovementsPrice[i*6+2]}", 60F, 30F+4*screenMovementsHeight/7)
        if(possibleMovements.size > i*6+3)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6+3]} $${possibleMovementsPrice[i*6+3]}", 60F, 30F+3*screenMovementsHeight/7)
        if(possibleMovements.size > i*6+4)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6+4]} $${possibleMovementsPrice[i*6+4]}", 60F, 30F+2*screenMovementsHeight/7)
        if(possibleMovements.size > i*6+5)
            game.font18?.draw(game.batch, "${possibleMovementsName[i*6+5]} $${possibleMovementsPrice[i*6+5]}", 60F, 30F+1*screenMovementsHeight/7)

        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F+5*(screenMovementsHeight-20F)/6 &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+(screenMovementsHeight-20F)){ //Toca no primeiro movimento
            if(Gdx.input.justTouched()){
                println("Primeiro movimento")
                escolhido = true
                chosenMove = i*6
            }
        }
        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F+4*(screenMovementsHeight-20F)/6 &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+5*(screenMovementsHeight-20F)/6){ //Toca no segundo movimento
            if(Gdx.input.justTouched()){
                println("Segundo movimento")
                escolhido = true
                chosenMove = i*6+1
            }
        }
        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F+3*(screenMovementsHeight-20F)/6 &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+4*(screenMovementsHeight-20F)/6){ //Toca no terceiro movimento
            if(Gdx.input.justTouched()){
                println("Terceiro movimento")
                escolhido = true
                chosenMove = i*6+2
            }
        }
        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F+2*(screenMovementsHeight-20F)/6 &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+3*(screenMovementsHeight-20F)/6){ //Toca no quarto movimento
            if(Gdx.input.justTouched()){
                println("Quarto movimento")
                escolhido = true
                chosenMove = i*6+3
            }
        }
        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F+(screenMovementsHeight-20F)/6 &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+2*(screenMovementsHeight-20F)/6){ //Toca no quinto movimento
            if(Gdx.input.justTouched()){
                println("Quinto movimento")
                escolhido = true
                chosenMove = i*6+4
            }
        }
        if(Gdx.input.getX().toFloat() > 30F && Gdx.input.getX().toFloat() < screenWidth-30F && screenHeight - Gdx.input.getY().toFloat() > 30F &&
            screenHeight - Gdx.input.getY().toFloat() < 30F+(screenMovementsHeight-20F)/6){ //Toca no sexto movimento
            if(Gdx.input.justTouched()){
                println("Sexto movimento")
                escolhido = true
                chosenMove = i*6+5
            }
        }
    }

    fun printCarteira(){
        game.font18?.draw(game.batch, "Carteira: $${player.carteira}", 20F+2*buttonWidth+40F , screenHeight-40F-20F,
            screenWidth-3*buttonWidth-5*20F, 1, true)
    }

}
