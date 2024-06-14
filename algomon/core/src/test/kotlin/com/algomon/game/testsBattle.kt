package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestBattle{

    var skills = mutableListOf<Movement>()
    var move = Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
        0, 0, 0, 0, 0, 0, 100, 0)
    val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)
    val enemy = Enemy("Pedrão", 500, 140, skills, 80, 80, 5, 80, 6)

    init{
        skills.add(move)
    }

    @Test
    fun testUpdatePlayerData(){
        updatePlayerData(player, enemy)
        assertEquals(player.hpbase, 53)
        assertEquals(player.staminabase, 75)
        assertEquals(player.atkbase, 41)
        assertEquals(player.defbase, 41)
        assertEquals(player.speedbase, 41)
        assertEquals(player.carteira, 125)
    }

}
