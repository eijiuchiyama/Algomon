package com.algomon.game.system

import com.algomon.game.component.InteractComponent
import com.algomon.game.component.InteractState
import com.algomon.game.component.InteractableComponent
import com.algomon.game.component.PhysicComponent
import com.algomon.game.component.PlayerComponent
import com.algomon.game.system.EntitySpawnSystem.Companion.HIT_BOX_SENSOR
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.World
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import ktx.box2d.query
import ktx.math.component1
import ktx.math.component2

@AllOf([InteractComponent::class, PhysicComponent::class])
class InteractSystem (
    private val interactCmps: ComponentMapper<InteractComponent>,
    private val physicCmps: ComponentMapper<PhysicComponent>,
    private val interactableCmps: ComponentMapper<InteractableComponent>,
    private val playerCmps: ComponentMapper<PlayerComponent>,
    private val phWorld: World
) : IteratingSystem() {
    override fun onTickEntity(entity: Entity) {
        val interactCmp = interactCmps[entity]

        if (interactCmp.isReady && !interactCmp.doInteract){
            return
        }

        if (interactCmp.isPrepared && interactCmp.doInteract){
            interactCmp.doInteract = false
            interactCmp.state = InteractState.INTERACTING
            interactCmp.delay = interactCmp.maxDelay
            return
        }

        interactCmp.delay -= deltaTime
        if (interactCmp.delay <= 0f && interactCmp.isInteracting){
            interactCmp.state = InteractState.DO_ACTION

            val physicCmp = physicCmps[entity]
            val direction = 1
            val (x, y) = physicCmp.body.position
            val (offX, offY) = physicCmp.offset
            val (w, h) = physicCmp.size
            val halfW = w * 0.5f
            val halfH = h * 0.5f

            AABB_RECT.set(
                x + offX - halfW - interactCmp.extraRange,
                y + offY - halfH,
                x + offX + halfW,
                y + offY + halfH
            )

            phWorld.query(AABB_RECT.x, AABB_RECT.y, AABB_RECT.width, AABB_RECT.height) { fixture ->
                if (fixture.userData != HIT_BOX_SENSOR) {
                    return@query true
                }

                val fixtureEntity = fixture.body.userData as Entity
                if (fixtureEntity == entity){
                    return@query true
                }

                configureEntity(fixtureEntity){
                    if (entity in playerCmps) {
                        interactableCmps.getOrNull(it)?.let { interactableCmp ->
                            interactableCmp.interactEntity = entity
                        }
                    }
                }

                return@query false
            }

            interactCmp.state = InteractState.READY
        }
    }

    companion object{
        // Axis Align Bounding Box
        val AABB_RECT = Rectangle()
    }
}
