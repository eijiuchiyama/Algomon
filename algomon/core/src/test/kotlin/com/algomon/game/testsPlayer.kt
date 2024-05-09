package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestPlayer{

    var skills: List<Movement> = emptyList<Movement>() + Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
        0, 0, 0, 0, 0, 0, 100, 0)
    val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)
    val enemy = Enemy("Pedrão", 500, 140, skills, 80, 80, 5, 80, 6)
    //val db = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")

    @Test
    fun testGetMovementMovementData() {
        val data = player.getMovementData(enemy, 0)

        assertEquals(data[0], 0)
        assertEquals(data[1], -50)
        assertEquals(data[2], 0)
        assertEquals(data[3], 0)
        assertEquals(data[4], 0)
        assertEquals(data[5], 0)
        assertEquals(data[6], -60 )
        assertEquals(data[7], 0)
        assertEquals(data[8], 0)
        assertEquals(data[9], 0)
        assertEquals(data[10], 0)
        assertEquals(data[11], 0)
    }

    @Test
    fun testGetMovementName(){
        val name = player.getMovementName(0)
        assertEquals(name, "ataque")
    }

    @Test
    fun testGetBaseAccuracy(){
        val acc = player.getBaseAccuracy(0)
        assertEquals(acc, 100)
    }

    @Test
    fun testUseMovement(){
        var movementData = player.getMovementData(enemy, 0)
        var baseAccuracy = player.getBaseAccuracy(0)
        var res = player.useMovement(movementData, baseAccuracy, enemy)
        assertEquals(res, 0 or 1)
        player.skills[0].staminaown = -200
        player.stamina += 50
        movementData = player.getMovementData(enemy, 0)
        baseAccuracy = player.getBaseAccuracy(0)
        res = player.useMovement(movementData, baseAccuracy, enemy)
        assertEquals(res, -1)
    }

    @Test
    fun testResetStats(){
        player.hp -= 20
        player.atk -= 5
        player.stamina -=20
        player.def += 5
        player.ResetStats()
        assertEquals(player.hp, 50)
        assertEquals(player.stamina, 70)
        assertEquals(player.atk, 40)
        assertEquals(player.def, 40)
    }

}
