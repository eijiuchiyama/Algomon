package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

class TestGame{

    val db = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")

    @Test
    fun testGetPlayerData(){
        val playerData = getPlayerData(db)

        assertEquals(playerData[0], 100)
        assertEquals(playerData[1], 120)
        assertEquals(playerData[2], 20)
        assertEquals(playerData[3], 20)
        assertEquals(playerData[4], 7)
        assertEquals(playerData[5], 20)
    }

    @Test
    fun testGetPlayerMovements(){
        val playerMovements = getPlayerMovements(db)

        val menorId = 1000  //Pega o movimento com menor id
        var posMenorId = 0
        for(i in 0..playerMovements.size){
            if(playerMovements[0].id < menorId) {
                posMenorId = i
            }
        }

        assertEquals(playerMovements[posMenorId].id, 0)
        assertEquals(playerMovements[posMenorId].staminaown, -10)
        assertEquals(playerMovements[posMenorId].hpenemy, -20)
    }

    @Test
    fun testSpecialEnemyData(){
        val specialEnemyData = getSpecialEnemyData(db, 0)

        assertEquals(specialEnemyData[0], 100)
        assertEquals(specialEnemyData[1], 120)
        assertEquals(specialEnemyData[2], 16)
        assertEquals(specialEnemyData[3], 16)
        assertEquals(specialEnemyData[4], 10)
        assertEquals(specialEnemyData[5], 25)
        assertEquals(specialEnemyData[6], 0)
    }

    @Test
    fun testGetSpecialEnemyMovements(){
        val playerMovements = getSpecialEnemyMovements(db, 0)

        val menorId = 1000
        var posMenorId = 0
        for(i in 0..playerMovements.size){
            if(playerMovements[0].id < menorId) {
                posMenorId = i
            }
        }

        assertEquals(playerMovements[posMenorId].id, 0)
        assertEquals(playerMovements[posMenorId].staminaown, -10)
        assertEquals(playerMovements[posMenorId].hpenemy, -20)
    }

}
