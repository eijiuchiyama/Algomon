package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.Player
import com.algomon.game.getPlayerData
import com.algomon.game.getPlayerMovements
import com.algomon.game.player
import com.algomon.game.playerData
import com.algomon.game.playerMovements
import com.algomon.game.screen.GameScreen.Companion.log
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxScreen


class StartScreen(var game: Main) : KtxScreen {

    var ortographicCamera = OrthographicCamera()

    private val logoWidth = 300F
    private val logoHeight = 250F
    private val buttonWidth: Float = 200F
    private val buttonHeight = 70
    private val exitWidth: Float = 50F
    private val exitHeight = 50
    private val screenWidth: Float = 640F
    private val screenHeight: Float = 480F

    private val logoTexture = Texture("assets/startScreen/logo.png")
    private val buttonTexture = Texture("assets/startScreen/button.png")
    private val buttonHoverTexture = Texture("assets/startScreen/buttonHover.png")
    private val exitTexture = Texture("assets/startScreen/exit.png")
    private val exitHoverTexture = Texture("assets/startScreen/exitHover.png")

    private val music = Gdx.audio.newMusic(Gdx.files.internal(("assets/music/bitBeats1.mp3")))
    private val soundEffect = Gdx.audio.newMusic(Gdx.files.internal("assets/music/buttonSound.mp3"))

    init {

        playerData = getPlayerData()
        playerMovements = getPlayerMovements()
        player = Player("Player", playerData[0], playerData[1], playerMovements, playerData[2], playerData[3],
            playerData[4], playerData[5], 0, 0)
        println("${player.name} ${player.hp} ${player.stamina} ${player.atk} ${player.def} ${player.dodge} ${player.speed}")

        ortographicCamera.setToOrtho(false, 640F, 480F)
        music.play()
    }

    override fun show() {
        log.debug { "StartScreen gets shown" }

        super.show()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.35F, 0.7F, 0.25F, 1F)

        if(!music.isPlaying){
            music.play()
        }

        ortographicCamera.update()
        game.batch?.setProjectionMatrix(ortographicCamera.combined)

        game.batch?.begin()

        game.batch?.draw(logoTexture, 140F, 180F, logoWidth*1.2F, logoHeight*1.2F)

        //Start button
        if(Gdx.input.getX().toFloat() in screenWidth/2-buttonWidth/2..screenWidth/2 + buttonWidth/2 &&
            screenHeight - Gdx.input.getY().toFloat() in 100F..100F + buttonHeight){
            game.batch?.draw(buttonHoverTexture, screenWidth/2 - buttonWidth/2, 100F)

            if(Gdx.input.justTouched()){
                soundEffect.play()

                this.dispose()
                if(!game.containsScreen<IntroScreen>())
                    game.addScreen(IntroScreen(game))
                game.setScreen<IntroScreen>()
            }

        } else{
            game.batch?.draw(buttonTexture, screenWidth/2 - buttonWidth/2, 100F)
        }

        //Exit button
        if(Gdx.input.getX().toFloat() in screenWidth-30-exitWidth..screenWidth-30F &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + exitHeight){
            game.batch?.draw(exitHoverTexture, screenWidth-30-exitWidth, 30F)
            if(Gdx.input.justTouched()){
                Gdx.app.exit()
            }
        } else{
            game.batch?.draw(exitTexture, screenWidth-30-exitWidth, 30F)
        }

        game.font12?.draw(game.batch, "Criado por Fernando Yang e Lucas Eiji Uchiyama", 5F, 20F)

        game.batch?.end()

    }

    override fun dispose() {
        music.dispose()
    }
}
