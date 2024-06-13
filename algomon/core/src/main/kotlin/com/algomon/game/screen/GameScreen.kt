package com.algomon.game.screen

import com.algomon.game.Main
import com.algomon.game.component.AiComponent
import com.algomon.game.component.AiComponent.Companion.AiComponentListener
import com.algomon.game.component.FloatingTextComponent.Companion.FloatingTextComponentListener
import com.algomon.game.component.ImageComponent.Companion.ImageComponentListener
import com.algomon.game.component.PhysicComponent.Companion.PhysicComponentListener
import com.algomon.game.component.StateComponent.Companion.StateComponentListener
import com.algomon.game.event.MapChangeEvent
import com.algomon.game.event.fire
import com.algomon.game.input.PlayerKeyboardInputProcessor
import com.algomon.game.system.AiSystem
import com.algomon.game.system.AnimationSystem
import com.algomon.game.system.AudioSystem
import com.algomon.game.system.CameraSystem
import com.algomon.game.system.CollisionDespawnSystem
import com.algomon.game.system.CollisionSpawnSystem
import com.algomon.game.system.DebugSystem
import com.algomon.game.system.EntitySpawnSystem
import com.algomon.game.system.FloatingTextSystem
import com.algomon.game.system.InteractSystem
import com.algomon.game.system.InteractableSystem
import com.algomon.game.system.MoveSystem
import com.algomon.game.system.PhysicSystem
import com.algomon.game.system.RenderSystem
import com.algomon.game.system.StateSystem
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.github.quillraven.fleks.World
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.box2d.createWorld
import ktx.log.logger
import ktx.math.vec2

class GameScreen(var game: Main) : KtxScreen {

    private val gameStage: Stage = Stage(ExtendViewport(12f,9f))
    private val uiStage: Stage = Stage(ExtendViewport(960f,720f))
    private val textureAtlas = TextureAtlas("assets/graphic/gameObject.atlas")
    private var currentMap: TiledMap? = null
    private val phWorld = createWorld(gravity = vec2(0f,0f)).apply {
        autoClearForces = false
    }

    private val eworld: World = World{
        inject(gameStage)
        inject("uiStage", uiStage)
        inject(game)
        inject(textureAtlas)
        inject(phWorld)

        componentListener<ImageComponentListener>()
        componentListener<PhysicComponentListener>()
        componentListener<FloatingTextComponentListener>()
        componentListener<StateComponentListener>()
        componentListener<AiComponentListener>()

        system<EntitySpawnSystem>()
        system<CollisionSpawnSystem>()
        system<CollisionDespawnSystem>()
        system<MoveSystem>()
        system<InteractSystem>()
        system<InteractableSystem>()
        system<PhysicSystem>()
        system<AnimationSystem>()
        system<StateSystem>()
        system<AiSystem>()
        system<CameraSystem>()
        system<FloatingTextSystem>()
        system<RenderSystem>()
        system<AudioSystem>()
        system<DebugSystem>()
    }
    override fun show() {
        log.debug { "GameScreen gets shown" }

        eworld.systems.forEach { system ->
            if (system is EventListener){
                gameStage.addListener(system)
            }
        }
        currentMap = TmxMapLoader().load("assets/map/map.tmx")
        gameStage.fire(MapChangeEvent(currentMap!!))

        PlayerKeyboardInputProcessor(eworld)
    }

    override fun resize(width: Int, height: Int) {
        gameStage.viewport.update(width,height,true)
        uiStage.viewport.update(width,height,true)
    }
    override fun render(delta: Float) {
        val dt = delta.coerceAtMost(0.25f)
        GdxAI.getTimepiece().update(dt)
        eworld.update(dt)
    }

    override fun dispose() {
        gameStage.disposeSafely()
        uiStage.disposeSafely()
        textureAtlas.disposeSafely()
        eworld.dispose()
        currentMap?.disposeSafely()
        phWorld.disposeSafely()
    }
    companion object {
        val log = logger<GameScreen>()
    }
}
