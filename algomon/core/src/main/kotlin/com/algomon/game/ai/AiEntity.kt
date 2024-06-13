package com.algomon.game.ai

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationType
import com.algomon.game.component.InteractComponent
import com.algomon.game.component.MoveComponent
import com.algomon.game.component.PhysicComponent
import com.algomon.game.component.StateComponent
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import ktx.math.component1
import ktx.math.component2

private val TMP_RECT = Rectangle()

data class AiEntity (
    private val entity: Entity,
    private val world: World,
    private val animationCmps: ComponentMapper<AnimationComponent> = world.mapper(),
    private val moveCmps: ComponentMapper<MoveComponent> = world.mapper(),
    private val interactCmps: ComponentMapper<InteractComponent> = world.mapper(),
    private val stateCmps: ComponentMapper<StateComponent> = world.mapper(),
    private val physicCmps: ComponentMapper<PhysicComponent> = world.mapper()
){
    val position: Vector2
        get() = physicCmps[entity].body.position

    val wantsToRun: Boolean
        get() {
            val moveCmp= moveCmps[entity]
            return moveCmp.cos != 0f || moveCmp.sin != 0f
        }

    val wantsToInteract: Boolean
        get() = interactCmps.getOrNull(entity)?.doInteract ?: false

    val interactCmp: InteractComponent
        get() = interactCmps[entity]

    val moveCmp: MoveComponent
        get() = moveCmps[entity]

    val wantsToChangeDirection
        get() = moveCmp.changeDirection

    fun animation(type: AnimationType, mode: PlayMode = PlayMode.LOOP, resetAnimation: Boolean = false){
        with(animationCmps[entity]){
            nextAnimation(type)
            playMode = mode
            if(resetAnimation){
                stateTime = 0f
            }
        }
    }

    fun state(next: EntityState){
        with(stateCmps[entity]){
            nextState = next
        }
    }

    fun changeToPreviousState(){
        with(stateCmps[entity]){
            nextState = stateMachine.previousState
        }
    }

    fun startInteract(){
        with(interactCmps[entity]){
            startInteract()
        }
    }

    fun moveTo(target: Vector2, speed: Float){
        val(targetX, targetY) = target
        val physicCmp = physicCmps[entity]
        val (sourceX, sourceY) = physicCmp.body.position
        with(moveCmps[entity]){
            val angleRad = MathUtils.atan2(targetY - sourceY, targetX - sourceX)
            cos = MathUtils.cos(angleRad)
            sin = MathUtils.sin(angleRad)
            this.speed = speed
        }
    }

    fun inRange(range: Float, target: Vector2): Boolean{
        val physicCmp = physicCmps[entity]
        val (sourceX, sourceY) = physicCmp.body.position
        val (offX, offY) = physicCmp.offset
        var (sizeX, sizeY) = physicCmp.size
        sizeX += range
        sizeY += range

        TMP_RECT.set(
            sourceX + offX - sizeX*0.5f,
            sourceY + offY - sizeY*0.5f,
            sizeX,
            sizeY
        )

        return TMP_RECT.contains(target)
    }

    fun stopMovement(){
        with(moveCmps[entity]){
            cos = 0f
            sin = 0f
        }
    }
}
