package com.algomon.game.system

import com.algomon.game.component.AiComponent
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem

@AllOf([AiComponent::class])
class AiSystem (
    private val aiCmps: ComponentMapper<AiComponent>,
) : IteratingSystem(){
    override fun onTickEntity(entity: Entity) {
        with(aiCmps[entity]){
            behaviorTree.step()
        }
    }
}
