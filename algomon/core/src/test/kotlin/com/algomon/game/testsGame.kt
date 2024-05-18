package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

class TestGame{

    @Test
    suspend fun testGetPlayerData(){
        val playerData = getPlayerData()

        assertEquals(playerData[0], 100)
        assertEquals(playerData[1], 120)
        assertEquals(playerData[2], 20)
        assertEquals(playerData[3], 20)
        assertEquals(playerData[4], 7)
        assertEquals(playerData[5], 20)
    }

    @Test
    suspend fun testGetPlayerMovements(){
        val playerMovements = getPlayerMovements()

        var menorId = 1000  //Pega o movimento com menor id
        var posMenorId = 0
        for(i in 0..playerMovements.size - 1){
            if(playerMovements[i].id < menorId) {
                posMenorId = i
                menorId = playerMovements[i].id
            }
        }

        assertEquals(playerMovements[posMenorId].id, 0)
        assertEquals(playerMovements[posMenorId].staminaown, -10)
        assertEquals(playerMovements[posMenorId].hpenemy, -20)
    }

    @Test
    suspend fun testSpecialEnemyData(){
        val specialEnemyData = getSpecialEnemyData(0)

        assertEquals(specialEnemyData[0], 100)
        assertEquals(specialEnemyData[1], 120)
        assertEquals(specialEnemyData[2], 16)
        assertEquals(specialEnemyData[3], 16)
        assertEquals(specialEnemyData[4], 10)
        assertEquals(specialEnemyData[5], 25)
        assertEquals(specialEnemyData[6], 0)
    }

    @Test
    suspend fun testGetSpecialEnemyMovements(){
        val specialEnemyMovements = getSpecialEnemyMovements(0)

        var menorId = 1000
        var posMenorId = 0
        for(i in 0..specialEnemyMovements.size - 1){
            if(specialEnemyMovements[i].id < menorId) {
                posMenorId = i
                menorId = specialEnemyMovements[i].id
            }
        }

        assertEquals(specialEnemyMovements[posMenorId].id, 0)
        assertEquals(specialEnemyMovements[posMenorId].staminaown, -10)
        assertEquals(specialEnemyMovements[posMenorId].hpenemy, -20)
    }

}
