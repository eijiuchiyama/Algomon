package com.algomon.game.ai

import com.algomon.game.component.AnimationType
import com.algomon.game.component.Direction

enum class DefaultState : EntityState{
    IDLE{
        override fun enter(entity: AiEntity) {
            val moveCmp = entity.moveCmp
            moveCmp.changeDirection = false
            if(moveCmp.direction == Direction.FRONT) entity.animation(AnimationType.idleFront)
            if(moveCmp.direction == Direction.BACK) entity.animation(AnimationType.idleBack)
            if(moveCmp.direction == Direction.LEFT) entity.animation(AnimationType.idleLeft)
            if(moveCmp.direction == Direction.RIGHT) entity.animation(AnimationType.idleRight)
        }

        override fun update(entity: AiEntity) {
            when{
                entity.wantsToInteract -> entity.state(INTERACT)
                entity.wantsToRun -> entity.state(RUN)
            }
        }
    },

    RUN{
        override fun enter(entity: AiEntity) {
            val moveCmp = entity.moveCmp
            if(moveCmp.direction == Direction.FRONT) entity.animation(AnimationType.runFront)
            if(moveCmp.direction == Direction.BACK) entity.animation(AnimationType.runBack)
            if(moveCmp.direction == Direction.LEFT) entity.animation(AnimationType.runLeft)
            if(moveCmp.direction == Direction.RIGHT) entity.animation(AnimationType.runRight)
        }

        override fun update(entity: AiEntity) {
            when{
                entity.wantsToInteract -> entity.state(INTERACT)
                !entity.wantsToRun -> entity.state(IDLE)
                entity.wantsToChangeDirection -> entity.state(IDLE)
            }
        }
    },

    INTERACT{
        override fun enter(entity: AiEntity) {
            val moveCmp = entity.moveCmp
            if(moveCmp.direction == Direction.FRONT) entity.animation(AnimationType.idleFront)
            if(moveCmp.direction == Direction.BACK) entity.animation(AnimationType.idleBack)
            if(moveCmp.direction == Direction.LEFT) entity.animation(AnimationType.idleLeft)
            if(moveCmp.direction == Direction.RIGHT) entity.animation(AnimationType.idleRight)
            entity.startInteract()
        }

        override fun update(entity: AiEntity) {
            val interactCmp = entity.interactCmp
            if(interactCmp.isReady && !interactCmp.doInteract){
                entity.changeToPreviousState()
            } else if (interactCmp.isReady){
                entity.startInteract()
            }
        }
    }
}
