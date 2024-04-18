package com.algomon.game.component

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

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
    var atlasKey: String = "",
    var stateTime: Float = 0f,
    var playMode: PlayMode = PlayMode.LOOP
) {
    lateinit var animation: Animation<TextureRegionDrawable>
    var nextAnimation: String = NO_ANIMATION

    fun nextAnimation(atlasKey: String, type: AnimationType){
        this.atlasKey = atlasKey
        nextAnimation = "$atlasKey/${type.atlasKey}"
    }

    companion object{
        val NO_ANIMATION = ""
    }
}
