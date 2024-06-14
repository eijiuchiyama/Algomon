package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestCharacter{

    @Test
    fun testChangeStatus(){

        lateinit var movements: MutableList<Movement>
        val player = Player("Thomas", 50, 100, movements, 30, 30, 5, 40, 0, 100)
        var movementData: List<Int> = emptyList()
        movementData += -20
        movementData += -20
        movementData += 10
        movementData += 10
        movementData += 5
        movementData += 10

        player.ChangeStatus(movementData)
        assertEquals(player.hp, 30)
        assertEquals(player.stamina, 80)
        assertEquals(player.atk, 40)
        assertEquals(player.def, 40)
        assertEquals(player.dodge, 10)
        assertEquals(player.speed, 50)

    }


}
