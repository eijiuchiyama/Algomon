package com.algomon.game.ai

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationType
import com.algomon.game.component.InteractComponent
import com.algomon.game.component.MoveComponent
import com.algomon.game.component.StateComponent
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World

data class AiEntity (
    private val entity: Entity,
    private val world: World,
    private val animationCmps: ComponentMapper<AnimationComponent> = world.mapper(),
    private val moveCmps: ComponentMapper<MoveComponent> = world.mapper(),
    private val interactCmps: ComponentMapper<InteractComponent> = world.mapper(),
    private val stateCmps: ComponentMapper<StateComponent> = world.mapper()
){
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
}
