package com.algomon.game

import kotlinx.serialization.*

@Serializable
data class Movement(var id: Int, var name: String, var hpown: Int, var staminaown: Int, var atkown: Int, var defown: Int, var dodgeown: Int,
    var speedown: Int, var hpenemy: Int, var staminaenemy: Int, var atkenemy: Int, var defenemy: Int, var dodgeenemy: Int, var speedenemy: Int,
    var minlevel: Int, var baseaccuracy: Int, var price: Int)
