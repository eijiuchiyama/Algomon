package com.algomon.game.screen

import com.algomon.game.Enemy
import com.algomon.game.Main
import com.algomon.game.countBattle
import com.algomon.game.enemy
import com.algomon.game.getSpecialEnemyData
import com.algomon.game.getSpecialEnemyMovements
import com.algomon.game.getSpecialEnemyName
import com.algomon.game.nBattles
import com.algomon.game.player
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen


class Battle(var game: Main): KtxScreen{

    private var initialize = false
    private var textShown = false
    private var options = true
    private var playerTurn = false

    private val screenWidth = 640F
    private val screenHeight = 480F
    private val boxWidth = 640F
    private val boxHeight = 120F
    private val playerWidth = 150F
    private val playerHeight = 200F
    private val boxDataWidth = 150F
    private val boxDataHeight = 100F
    private val movementButtonWidth = 400F
    private val movementButtonHeight = 120F
    private val movementsBoxWidth = 640F
    private val movementsBoxHeight = 200F

    private val boxTexture = Texture("assets/battle/box.png")
    private val playerTexture = Texture("assets/battle/player.png")
    private val boxDataTexture = Texture("assets/battle/boxData.png")
    private val commonEnemyTexture = Texture("assets/battle/enemyCommon.png")
    private val specialEnemyTexture = Texture("assets/battle/enemySpecial.png")

    private val movementButtonTexture = Texture("assets/battle/movementButton.png")
    private val runButtonTexture = Texture("assets/battle/runButton.png")

    private val movementsBoxTexture = Texture("assets/battle/moveBox.png")

    private val music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/2dExplorer.mp3"))
    override fun show() {

    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.5F, 0.5F, 0.9F, 1F)

        if(!music.isPlaying) {
            music.play()
        }

        if(!initialize){
            initialize()
            getEnemy()
            if(player.speed >= enemy.speed)
                playerTurn = true
            else
                playerTurn = false
        }

        game.batch?.begin()
        game.batch?.draw(playerTexture, 0F, boxHeight, playerWidth*1.5F, playerHeight*1.5F)
        game.batch?.draw(boxDataTexture, playerWidth*1.5F+10F, boxHeight+100F)
        game.batch?.draw(commonEnemyTexture, screenWidth-playerWidth*1.2F,screenHeight-playerHeight*1.2F,playerWidth*1.2F, playerHeight*1.2F)
        game.batch?.draw(boxDataTexture, screenWidth-playerWidth*1.2F-boxDataWidth-10F, screenHeight-boxDataHeight-20F)

        showData()

        if(textShown == true) {
            showText(0)
        } else if(options == true){
            showOptions()
        } else{
            showMovements()
        }

        if(textShown == true){ //Toca na caixa de texto
            if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < 640F && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < 120F){
                if(Gdx.input.justTouched()){
                    textShown = false
                    options = true
                }
            }
        } else if(options == true){ //Toca nas opções
            if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < movementButtonWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < movementButtonHeight){ //Toca no botão movements
                if(Gdx.input.justTouched()){
                    options = false
                }
            }
            if(Gdx.input.getX().toFloat() > movementButtonWidth && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < movementButtonHeight){ //Toca no botão run
                if(Gdx.input.justTouched()){
                    this.dispose()
                    if(!game.containsScreen<IntervalMenu>())
                        game.addScreen(IntervalMenu(game))
                    game.setScreen<IntervalMenu>()
                }
            }
        } else{ //Toca nos movimentos
            if(Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth && screenHeight - Gdx.input.getY().toFloat() > 0F &&
                screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight){
                if(Gdx.input.justTouched()){
                    textShown = true
                }
            }
        }



        game.batch?.end()


    }

    //Exibe a caixa de texto
    fun showText(text: Int){
        game.batch?.draw(boxTexture, screenWidth/2 - boxWidth/2, 0F)
        if(text == 0){
            game.font18?.draw(game.batch, "Player usa Hacking\n\nHP de player vai a 200",
                20F, boxHeight-20F,
                boxWidth-20, -1, true)
        } else if(text == 1){
            game.font12?.draw(game.batch, "Inimigo usa Hacking\n\nHP de inimigo vai a 140",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        } else if(text == 2){
            game.font12?.draw(game.batch, "Player erra Hacking",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        } else if(text == 3){
            game.font12?.draw(game.batch, "Inimigo erra Hacking",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        } else if(text == 4) {
            game.font12?.draw(
                game.batch, "Player vence",
                20F, boxHeight - 20F,
                boxWidth - 20F, -1, true)
        } else if(text == 5){
            game.font12?.draw(game.batch, "Enemy vence",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        } else if(text == 6){
            game.font12?.draw(game.batch, "Player consegue fugir",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        } else if(text == 7){
            game.font12?.draw(game.batch, "Player não consegue fugir",
                20F, boxHeight-20F,
                boxWidth-20F, -1, true)
        }
    }

    fun showData(){
        game.font12?.draw(game.batch, "Player\n\nHP: ${player.hp}/${player.hpbase}\n\n" +
            "Stamina: ${player.stamina}/${player.staminabase}", playerWidth*1.5F+10F+10F, boxHeight+100F+boxDataHeight-10F,
            boxDataWidth-10F, -1, true)

        game.font12?.draw(game.batch, "${enemy.name}\n\nHP: ${enemy.hp}/${enemy.hpbase}\n\n" +
            "Stamina: ${enemy.stamina}/${enemy.staminabase}", screenWidth-playerWidth*1.2F-10F-boxDataWidth+10F, screenHeight-20F-10F,
            boxDataWidth-10F, -1, true)
    }

    fun showOptions(){
        game.batch?.draw(movementButtonTexture, 0F, 0F)
        game.batch?.draw(runButtonTexture, movementButtonWidth, 0F)
    }

    fun showMovements(){
        game.batch?.draw(movementsBoxTexture, 0F, 0F)
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
    }

    //Inicializa os status do jogador para iniciar a batalha
    fun initialize(){
        player.hp = player.hpbase
        player.stamina = player.staminabase
        player.atk = player.atkbase
        player.def = player.defbase
        player.dodge = player.dodgebase
        player.speed = player.speedbase
    }

    fun getEnemy(){
        val enemyName = getSpecialEnemyName(countBattle)
        val enemyData = getSpecialEnemyData(countBattle)
        val enemyMovements = getSpecialEnemyMovements(countBattle)
        enemy = Enemy(enemyName, enemyData[0], enemyData[1], enemyMovements, enemyData[2], enemyData[3], enemyData[4],
            enemyData[5], player.level)
    }
}
