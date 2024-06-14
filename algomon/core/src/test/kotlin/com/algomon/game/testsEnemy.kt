package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestEnemy {

    lateinit var skills: MutableList<Movement>
    var move1 = Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
        0, 0, 0, 0, 0, 0, 100, 0)
    var move2 = Movement(1, "superataque",
        0, -100, 0, 0, 0, 0, -200, 0, 0, 0, 0, 0,
        1, 100, 100)
    val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)
    val enemy = Enemy("Pedrão", 100, 140, skills, 80, 80, 5, 80, 6)

    init{
        skills.add(move1)
        skills.add(move2)
    }

    @Test
    fun testGetMovementData(){
        val random = 1
        val movementData = enemy.getMovementData(player, random)
        assertEquals(movementData[0], 0)
        assertEquals(movementData[1], -100)
        assertEquals(movementData[6], -240)
    }

    @Test
    fun testUseMovement(){
        val random = 1
        var movementData = enemy.getMovementData(player, random)
        var res = enemy.useMovement(movementData, skills[random].baseaccuracy, player)
        assertEquals(res, 0 or 1)
        enemy.skills[random].staminaown = -200
        enemy.stamina += 100
        movementData = enemy.getMovementData(player, random)
        res = enemy.useMovement(movementData, skills[random].baseaccuracy, player)
        assertEquals(res, -1)
    }



}
