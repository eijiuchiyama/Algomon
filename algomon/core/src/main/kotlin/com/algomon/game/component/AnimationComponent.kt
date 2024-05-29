package com.algomon.game.component

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

enum class AnimationModel{
    player, slime, door, shelf, computer, UNDEFINED;

    val atlasKey: String = this.toString()
}
enum class AnimationType{
    idleFront, idleSide, idleBack,
    runFront, runSide, runBack,
    attackFront, attackSide, attackBack,
    attacked,
    death,
    open;

    val atlasKey: String = this.toString()
}
class AnimationComponent(
    var model: AnimationModel = AnimationModel.UNDEFINED,
    var stateTime: Float = 0f,
    var playMode: PlayMode = PlayMode.LOOP
) {
    lateinit var animation: Animation<TextureRegionDrawable>
    var nextAnimation: String = NO_ANIMATION

    fun nextAnimation(model: AnimationModel, type: AnimationType){
        this.model = model
        nextAnimation = "${model.atlasKey}/${type.atlasKey}"
    }

    companion object{
        val NO_ANIMATION = ""
    }
}
