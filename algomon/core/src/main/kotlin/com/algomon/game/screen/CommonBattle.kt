package com.algomon.game.screen

import com.algomon.game.Enemy
import com.algomon.game.Main
import com.algomon.game.countBattle
import com.algomon.game.enemy
import com.algomon.game.getCommonEnemyData
import com.algomon.game.getCommonEnemyMovements
import com.algomon.game.getCommonEnemyName
import com.algomon.game.getPossibleEnemies
import com.algomon.game.getRandom
import com.algomon.game.nBattles
import com.algomon.game.player
import com.algomon.game.updatePlayerData
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen

class CommonBattle(var game: Main): KtxScreen {

    private var initialize = false
    private var textShown = false
    private var options = true
    private var playerTurn = false
    private var movimentoPlayer = 0
    private var movimentoEnemy = 0
    private var text = 0

    private val screenWidth = 640F
    private val screenHeight = 480F
    private val boxWidth = 640F
    private val boxHeight = 120F
    private val playerWidth = 150F
    private val playerHeight = 200F
    private val boxDataWidth = 200F
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

    override fun render(delta: Float) {
        ScreenUtils.clear(0.5F, 0.5F, 0.9F, 1F)

        if(!music.isPlaying) {
            music.play()
        }

        //Inicializa os status do jogador no início da batalha
        if(!initialize){
            initialize()
            getEnemy()
            if(player.speed >= enemy.speed)
                playerTurn = true
            else
                playerTurn = false
            initialize = true
        }

        game.batch?.begin()
        game.batch?.draw(playerTexture, 0F, boxHeight, playerWidth*1.5F, playerHeight*1.5F)
        game.batch?.draw(boxDataTexture, playerWidth*1.5F+10F, boxHeight+100F)
        game.batch?.draw(commonEnemyTexture, screenWidth-playerWidth*1.2F,screenHeight-playerHeight*1.2F,playerWidth*1.2F, playerHeight*1.2F)
        game.batch?.draw(boxDataTexture, screenWidth-playerWidth*1.2F-boxDataWidth-10F, screenHeight-boxDataHeight-20F)
        showData()

        if(playerTurn) { //Se for turno do jogador
            if (textShown == true) {
                showText()
            } else if (options == true) {
                showOptions()
            } else {
                showMovements()
            }
        } else{ //Se for turno do inimigo
            if(textShown == true){
                showText()
            } else {
                val random = kotlin.random.Random.nextInt(0, enemy.skills.size)
                movimentoEnemy = random
                val movementData = enemy.getMovementData(player, random)
                val movementName = enemy.skills[random].name
                val baseAccuracy = enemy.skills[random].baseaccuracy
                val success = enemy.useMovement(movementData, baseAccuracy, player)
                textShown = true
                if (success == 1) { //Se o ataque do inimigo foi bem-sucedido
                    text = 1
                } else if (success == 0) { //Se errou
                    text = 3
                } else { //Se stamina é insuficiente
                    text = 5
                }
                playerTurn = true
            }
        }

        game.batch?.end()

    }

    //Exibe a caixa de texto
    fun showText(){
        game.batch?.draw(boxTexture, screenWidth/2 - boxWidth/2, 0F)
        if(text == 0){
            game.font18?.draw(game.batch, "Você usou ${player.skills[movimentoPlayer].name}",
                20F, boxHeight-20F,
                boxWidth-40, -1, true)
        } else if(text == 1){
            game.font18?.draw(game.batch, "${enemy.name} usou ${enemy.skills[movimentoEnemy].name}",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 2){
            game.font18?.draw(game.batch, "Você errou ${player.skills[movimentoPlayer].name}",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 3){
            game.font18?.draw(game.batch, "${enemy.name} errou ${enemy.skills[movimentoEnemy].name}",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 4){
            game.font18?.draw(game.batch, "A sua stamina não é suficiente para realizar o movimento",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 5){
            game.font18?.draw(game.batch, "A stamina de ${enemy.name} não é suficiente para realizar o movimento",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 6) {
            game.font18?.draw(game.batch, "Parabéns! Você venceu",
                20F, boxHeight - 20F,
                boxWidth - 40F, -1, true)
        } else if(text == 7){
            game.font18?.draw(game.batch, "${enemy.name} venceu",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 8){
            game.font18?.draw(game.batch, "Você conseguiu fugir",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        } else if(text == 9){
            game.font18?.draw(game.batch, "Você não conseguiu fugir",
                20F, boxHeight-20F,
                boxWidth-40F, -1, true)
        }

        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < 640F && screenHeight - Gdx.input.getY().toFloat() > 0F &&
            screenHeight - Gdx.input.getY().toFloat() < 120F){
            if (Gdx.input.justTouched()){
                if(enemy.hp != 0 && player.hp != 0) {
                    textShown = false
                    options = true
                }

                if(text == 6){ //Vence a batalha
                    updatePlayerData(player, enemy)
                    if(countBattle == nBattles){
                        this.dispose()
                        if(!game.containsScreen<YouWin>())
                            game.addScreen(YouWin(game))
                        game.setScreen<YouWin>()
                    } else {
                        this.dispose()
                        if (!game.containsScreen<GameScreen>())
                            game.addScreen(GameScreen(game))
                        game.setScreen<GameScreen>()
                    }
                }
                if(text == 7){ //Perde a batalha
                    this.dispose()
                    if(!game.containsScreen<GameScreen>())
                        game.addScreen(GameScreen(game))
                    game.setScreen<GameScreen>()
                }
                if(text == 8){ //Consegue fugir
                    this.dispose()
                    if (!game.containsScreen<GameScreen>())
                        game.addScreen(GameScreen(game))
                    game.setScreen<GameScreen>()
                }

                if(enemy.hp == 0)
                    text = 6
                if(player.hp == 0)
                    text = 7
            }
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
        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < movementButtonWidth &&
            screenHeight - Gdx.input.getY().toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementButtonHeight
        ) { //Toca no botão movements
            if (Gdx.input.justTouched()) {
                options = false
            }
        }
        if (Gdx.input.getX().toFloat() > movementButtonWidth && Gdx.input.getX().toFloat() < screenWidth &&
            screenHeight - Gdx.input.getY().toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementButtonHeight
        ) { //Toca no botão run
            if (Gdx.input.justTouched()) {
                if(kotlin.random.Random.nextInt(0, 2) == 0)
                    text = 8
                else
                    text = 9
                textShown = true
            }
        }
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
        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth / 3 &&
            screenHeight - Gdx.input.getY().toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight
        ) { //Toca no primeiro movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 0 escolhido")
                movimentoPlayer = 0
                val movement = player.getMovementData(enemy, 0)
                val baseAccuracy = player.getBaseAccuracy(0)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 0
                textShown = true
                playerTurn = false
            }
        }
        if (Gdx.input.getX().toFloat() > screenWidth / 3 && Gdx.input.getX().toFloat() < 2 * screenWidth / 3 &&
            screenHeight - Gdx.input.getY().toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight
        ) { //Toca no segundo movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 1 escolhido")
                val movement = player.getMovementData(enemy, 1)
                val baseAccuracy = player.getBaseAccuracy(1)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 1
                textShown = true
                playerTurn = false
            }
        }
        if (Gdx.input.getX().toFloat() > 2 * screenWidth / 3 && Gdx.input.getX().toFloat() < screenWidth &&
            screenHeight - Gdx.input.getY().toFloat() > movementsBoxHeight / 2 && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight
        ) { //Toca no terceiro movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 2 escolhido")
                val movement = player.getMovementData(enemy, 2)
                val baseAccuracy = player.getBaseAccuracy(2)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 2
                textShown = true
                playerTurn = false
            }
        }
        if (Gdx.input.getX().toFloat() > 0F && Gdx.input.getX().toFloat() < screenWidth / 3 &&
            screenHeight - Gdx.input.getY().toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight / 2
        ) { //Toca no quarto movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 3 escolhido")
                val movement = player.getMovementData(enemy, 3)
                val baseAccuracy = player.getBaseAccuracy(3)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 3
                textShown = true
                playerTurn = false
            }
        }
        if (Gdx.input.getX().toFloat() > screenWidth / 3 && Gdx.input.getX().toFloat() < 2 * screenWidth / 3 &&
            screenHeight - Gdx.input.getY().toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight / 2
        ) { //Toca no quinto movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 4 escolhido")
                val movement = player.getMovementData(enemy, 4)
                val baseAccuracy = player.getBaseAccuracy(4)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 4
                textShown = true
                playerTurn = false
            }
        }
        if (Gdx.input.getX().toFloat() > 2 * screenWidth / 3 && Gdx.input.getX().toFloat() < screenWidth &&
            screenHeight - Gdx.input.getY().toFloat() > 0F && screenHeight - Gdx.input.getY().toFloat() < movementsBoxHeight / 2
        ) { //Toca no sexto movimento
            if (Gdx.input.justTouched()) {
                println("Movimento 5 escolhido")
                val movement = player.getMovementData(enemy, 5)
                val baseAccuracy = player.getBaseAccuracy(5)
                val success = player.useMovement(movement, baseAccuracy, enemy)
                if(success == 1)
                    text = 0
                else if(success == 0)
                    text = 2
                else
                    text = 4
                movimentoPlayer = 5
                textShown = true
                playerTurn = false
            }
        }
    }

    //Pega os dados do inimigo
    fun getEnemy(){
        val id = getPossibleEnemies(player)
        val random = getRandom(id)
        val enemyName = getCommonEnemyName(random)
        val enemyData = getCommonEnemyData(random)
        val enemyMovements = getCommonEnemyMovements(player)
        enemy = Enemy(enemyName, enemyData[0], enemyData[1], enemyMovements, enemyData[2], enemyData[3], enemyData[4], enemyData[5], player.level)
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

}
