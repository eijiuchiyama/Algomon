package com.algomon.game.system

import com.algomon.game.component.AnimationComponent
import com.algomon.game.component.AnimationComponent.Companion.NO_ANIMATION
import com.algomon.game.component.ImageComponent
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem

@AllOf([AnimationComponent::class, ImageComponent::class])
class AnimationSystem(
    private val animationCmps: ComponentMapper<AnimationComponent>,
    private val imageCmps: ComponentMapper<ImageComponent>
): IteratingSystem() {
    override fun onTickEntity(entity: Entity) {
        val aniCmp = animationCmps[entity]

        imageCmps[entity].image.drawable = if(aniCmp.nextAnimation = NO_ANIMATION) {
            aniCmp.stateTime += deltaTime
            aniCmp.animation.playMode = aniCmp.playMode
            val frame = aniCmp.animation.getKeyFrame(aniCmp.stateTime)
        }else {

        }
    }
}
