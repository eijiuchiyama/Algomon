package com.algomon.game.system

import com.algomon.game.component.ImageComponent
import com.algomon.game.component.PhysicComponent
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.World
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.Fixed
import com.github.quillraven.fleks.IteratingSystem
import ktx.log.logger
import ktx.math.component1
import ktx.math.component2

@AllOf([PhysicComponent::class, ImageComponent::class])
class PhysicSystem (
    private val phworld : World,
    private val imageCmps : ComponentMapper<ImageComponent>,
    private val physicCmps : ComponentMapper<PhysicComponent>
):IteratingSystem(interval = Fixed(1/60f)){
    override fun onUpdate() {
        if (phworld.autoClearForces){
            log.error { "AutoClearForce must be set to false" }
            phworld.autoClearForces = false
        }
        super.onUpdate()
        phworld.clearForces()
    }

    override fun onTick() {
        super.onTick()
        phworld.step(deltaTime,6,2)
    }
    override fun onTickEntity(entity: Entity) {
        val physicCmp = physicCmps[entity]

        physicCmp.prevPos.set(physicCmp.body.position)

        if (!physicCmp.impulse.isZero){
            physicCmp.body.applyLinearImpulse(physicCmp.impulse, physicCmp.body.worldCenter, true)
            physicCmp.impulse.setZero()
        }
    }

    override fun onAlphaEntity(entity: Entity, alpha: Float) {
        val physicCmp = physicCmps[entity]
        val imageCmp = imageCmps[entity]

        val (prevX, prevY) = physicCmp.prevPos
        val (bodyX, bodyY) = physicCmp.body.position
        imageCmp.image.run{
            setPosition(
                MathUtils.lerp(prevX,bodyX,alpha) - width * 0.5f,
                MathUtils.lerp(prevY,bodyY,alpha) - height * 0.5f
            )
        }
    }

    companion object{
        private val log = logger<PhysicSystem>()
    }
}
