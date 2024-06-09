package com.algomon.game.screen

import com.algomon.game.Main
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.Timer
import kotlinx.coroutines.delay
import ktx.app.KtxScreen

class IntroScreen(var game: Main): KtxScreen {

    private val nextWidth = 100F
    private val nextHeight = 50F
    private val skipAllWidth = 150F
    private val skipAllHeight = 50F
    private val screenTextureWidth = 600F
    private val screenTextureHeight = 350F
    private val screenWidth = 640F
    private val screenHeight = 480F

    private var texto = 0

    private val nextTexture = Texture("assets/intro/next.png")
    private val skipAllTexture = Texture("assets/intro/skipAll.png")
    private val screenTexture = Texture("assets/intro/screen.png")

    private val music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/bitBeats4.mp3"))
    private val soundEffect = Gdx.audio.newSound(Gdx.files.internal("assets/music/buttonSound.mp3"))

    init{
        music.play()
    }

    override fun show() {
        GameScreen.log.debug { "IntroScreen gets shown" }

        super.show()
    }

    override fun render(delta: Float) {

        ScreenUtils.clear(0.2F, 0.2F, 0.2F, 1F)

        if(!music.isPlaying){
            music.play()
        }

        println(texto)

        game.batch?.begin()

        game.batch?.draw(screenTexture, screenWidth/2 - screenTextureWidth/2, 100F)
        game.batch?.draw(nextTexture, screenWidth-nextWidth-30, 30F)
        game.batch?.draw(skipAllTexture, 30F, 30F)

        printOpening()

        if(Gdx.input.getX().toFloat() in 30F..30F + skipAllWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + skipAllHeight){
            if(Gdx.input.justTouched()){
                soundEffect.play()

                this.dispose()
                game.addScreen(Battle(game))
                game.setScreen<Battle>()

            }
        }

        if(Gdx.input.getX().toFloat() in screenWidth-30-nextWidth..screenWidth-30+skipAllWidth &&
            screenHeight - Gdx.input.getY().toFloat() in 30F..30F + screenHeight){
            if(Gdx.input.justTouched()){
                soundEffect.play()
                if(texto < 5) {
                    texto++
                } else{
                    this.dispose()
                    game.addScreen(Battle(game))
                    game.setScreen<Battle>()
                }
            }
        }

        game.batch?.end()

    }

    override fun dispose() {
        music.dispose()
    }

    fun printOpening(){
        if(texto == 0) {
            game.font18?.draw(
                game.batch, "Parabens! Você acaba de se inscrever no nosso torneio!",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        } else if(texto == 1){
            game.font18?.draw(
                game.batch, "Se você deseja provar que é o melhor hacker do mundo, está no caminho certo!",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        } else if(texto == 2){
            game.font18?.draw(
                game.batch, "Aqui, você competirá com outros hackers utilizando código. Seu objetivo é " +
                    "invadir o computador de seu adversário.",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        } else if(texto == 3){
            game.font18?.draw(
                game.batch, "Eis as regras:\n" +
                    "   Você deve vencer todas as batalhas do torneio para ser o vencedor.\n" +
                    "   São doze batalhas principais, cada vez contra um oponente mais forte.\n" +
                    "   Ataque seu adversário até que o HP caia dele para zero. Busque conservar sua stamina.\n" +
                    "   Entre cada batalha você pode escolher não fazer nada, treinar ou aprender novos movimentos.\n" +
                    "   Se você perder uma batalha do torneio será eliminado.",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        } else if(texto == 4){
            game.font18?.draw(
                game.batch, "Tome cuidado para não perder seu HP e ser eliminado. O próximo torneio é só ano que vem!",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        } else if(texto == 5){
            game.font18?.draw(
                game.batch, "Acho que é tudo. Boa sorte!",
                screenWidth / 2 - screenTextureWidth / 2 + 30, screenHeight - 60,
                screenTextureWidth - 60, -1, true
            )
        }
    }
}
