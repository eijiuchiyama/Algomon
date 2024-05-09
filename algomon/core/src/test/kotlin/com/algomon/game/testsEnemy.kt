package com.algomon.game

import org.junit.Test

var skills: List<Movement> = emptyList<Movement>() + Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
    0, 0, 0, 0, 0, 0, 100, 0) + Movement(1, "superataque",
        0, -100, 0, 0, 0, 0, -200, 0, 0, 0, 0, 0,
       1, 100, 100)
val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)
val enemy = Enemy("Pedrão", 100, 140, skills, 80, 80, 5, 80, 6)

internal class TestEnemy {

    @Test
    fun testGetMovementData(){

    }



}
