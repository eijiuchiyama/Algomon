package com.algomon.game.ai

import com.algomon.game.component.AnimationType
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.btree.LeafTask
import com.badlogic.gdx.ai.btree.Task
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute
import com.badlogic.gdx.ai.utils.random.FloatDistribution
import com.badlogic.gdx.math.MathUtils
import ktx.math.vec2

abstract class Action: LeafTask<AiEntity>(){
    val entity: AiEntity
        get() = `object`

    override fun copyTo(task: Task<AiEntity>) = task
}

class IdleTask(
    @JvmField
    @TaskAttribute(required = true)
    var duration: FloatDistribution? = null
) : Action(){
    private var currentDuration = 0f
    override fun execute(): Status {
        if(status != Status.RUNNING){
            entity.animation(AnimationType.idleFront)
            currentDuration = duration?.nextFloat() ?: 1f
            return Status.RUNNING
        }

        currentDuration -= GdxAI.getTimepiece().deltaTime
        if(currentDuration <= 0f){
            return Status.SUCCEEDED
        }

        return Status.RUNNING
    }

    override fun copyTo(task: Task<AiEntity>): Task<AiEntity> {
        (task as IdleTask).duration = duration
        return task
    }
}

class RunTask : Action(){
    private val targetPos = vec2()
    private var currentDuration = 0f
    private var speed = 5f

    override fun execute(): Status {
        if(status != Status.RUNNING){
            entity.animation(AnimationType.runFront)
            targetPos.set(entity.position)
            targetPos.x += MathUtils.random(-3f, 3f)
            targetPos.y += MathUtils.random(-3f, 3f)
            speed = MathUtils.random(1f, 3f)
            entity.moveTo(targetPos, speed)
            currentDuration = 1f
            return Status.RUNNING
        }

        currentDuration -= GdxAI.getTimepiece().deltaTime
        if(currentDuration <= 0f){
            entity.stopMovement()
            return Status.SUCCEEDED
        }

        if(entity.inRange(0.5f, targetPos)){
            entity.stopMovement()
            return Status.SUCCEEDED
        }

        return Status.RUNNING
    }
}
