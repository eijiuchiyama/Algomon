package com.algomon.game.component

enum class InteractState {
    READY, PREPARE, INTERACTING, DO_ACTION
}
class InteractComponent (
    var doInteract: Boolean = false,
    var state: InteractState = InteractState.READY,
    var delay: Float = 0f,
    var maxDelay: Float = 0f,
    var extraRange: Float = 0f
){
    val isReady: Boolean
        get() = state == InteractState.READY

    val isPrepared: Boolean
        get() = state == InteractState.PREPARE

    val isInteracting: Boolean
        get() = state == InteractState.INTERACTING

    fun startInteract(){
        state = InteractState.PREPARE
    }
}
