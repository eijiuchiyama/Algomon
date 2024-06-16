package com.algomon.game.component

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

enum class AnimationModel{
    player, slime, door, door2, shelf, computer, computer2, exit, UNDEFINED;

    val atlasKey: String = this.toString()
}
enum class AnimationType{
    idleFront, idleLeft, idleRight, idleBack,
    runFront, runLeft, runRight, runBack,
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

    fun nextAnimation(type: AnimationType){
        nextAnimation = "${model.atlasKey}/${type.atlasKey}"
    }

    companion object{
        val NO_ANIMATION = ""
    }
}
