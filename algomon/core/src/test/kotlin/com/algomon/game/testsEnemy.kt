package com.algomon.game

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

var skills: List<Movement> = emptyList<Movement>() + Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
    0, 0, 0, 0, 0, 0, 100, 0) + Movement(1, "superataque",
        0, -100, 0, 0, 0, 0, -200, 0, 0, 0, 0, 0,
       1, 100, 100)
val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)
val enemy = Enemy("Pedrão", 100, 140, skills, 80, 80, 5, 80, 6)

internal class TestEnemy {

    @Test
    fun testGetMovementData(){
        val random = 1
        val movementData = enemy.getMovementData(player, 1)
        assertEquals(movementData[0], 0)
        assertEquals(movementData[1], -100)
        assertEquals(movementData[6], -240)
    }

    @Test
    fun testUseMovement(){
        val random = 1
        var movementData = enemy.getMovementData(player, random)
        var res = enemy.useMovement(movementData, skills[random].baseaccuracy, player)
        Assert.assertEquals(res, 0 or 1)
        enemy.skills[random].staminaown = -200
        enemy.stamina += 100
        movementData = enemy.getMovementData(player, random)
        res = enemy.useMovement(movementData, skills[random].baseaccuracy, player)
        Assert.assertEquals(res, -1)
    }



}
