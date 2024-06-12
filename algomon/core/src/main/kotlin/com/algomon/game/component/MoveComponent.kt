package com.algomon.game.component

enum class Direction{
    LEFT, RIGHT, FRONT, BACK, NULL
}
data class MoveComponent(
    var speed: Float = 0f,
    var cos: Float = 0f,
    var sin: Float = 0f,
    var direction: Direction = Direction.FRONT,
    var changeDirection: Boolean = false
)
