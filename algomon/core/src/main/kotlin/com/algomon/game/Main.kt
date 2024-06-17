package com.algomon.game

import com.algomon.game.screen.BuyMovementMenu
import com.algomon.game.screen.GameScreen
import com.algomon.game.screen.StartScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ktx.app.KtxGame
import ktx.app.KtxScreen

//Define as variáveis globais
var playerData = emptyList<Int>()
var playerMovements = mutableListOf<Movement>()
var player = Player("",0,0, mutableListOf<Movement>(), 0,0,0,0,0,0)
var enemy = Enemy("", 0, 0, mutableListOf<Movement>(), 0,0,0, 0, 0)
var countBattle = 0
var nBattles = 2

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : KtxGame<KtxScreen>(){

    var batch: SpriteBatch? = null
    var font18: BitmapFont? = null
    var font12: BitmapFont? = null
    var fontGameOver: BitmapFont? = null
    var fontYouWin: BitmapFont? = null

    override fun create(){

        playerData = getPlayerData()
        playerMovements = getPlayerMovements()
        player = Player("Player", playerData[0], playerData[1], playerMovements, playerData[2], playerData[3],
            playerData[4], playerData[5], 0, 0)
        println("${player.name} ${player.hp} ${player.stamina} ${player.atk} ${player.def} ${player.dodge} ${player.speed}")
        player.level = 10
        player.carteira = 1000

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
        addScreen(StartScreen(this))
        setScreen<StartScreen>()
    }

    companion object{
        const val UNIT_SCALE = 1 / 16f
    }
}
