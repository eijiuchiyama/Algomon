package com.algomon.game.system

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationModel
import com.algomon.game.component.AnimationType
import com.algomon.game.component.InteractableComponent
import com.algomon.game.component.PhysicComponent
import com.badlogic.gdx.graphics.g2d.Animation
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem

@AllOf([InteractableComponent::class])
class InteractableSystem(
    private val intableCmps: ComponentMapper<InteractableComponent>,
    private val aniCmps: ComponentMapper<AnimationComponent>,
    private val physicCmps: ComponentMapper<PhysicComponent>
) : IteratingSystem() {
    override fun onTickEntity(entity: Entity) {
        with(intableCmps[entity]){
            val aniCmp = aniCmps[entity]
            if (interactEntity == null){
                return
            }

            if (aniCmp.model == AnimationModel.door){
                configureEntity(entity){ physicCmps.remove(it) }
                /*aniCmp.nextAnimation(AnimationType.open)
                aniCmp.playMode = Animation.PlayMode.NORMAL*/
            }
        }
    }
}
