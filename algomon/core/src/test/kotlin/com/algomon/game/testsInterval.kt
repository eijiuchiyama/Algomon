package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

class TestInterval{
    val db = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")
    var skills: List<Movement> = emptyList<Movement>() + Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
        0, 0, 0, 0, 0, 0, 100, 0)
    val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)

    @Test
    fun testGetPossibleEnemies(){
        val possibleEnemies = getPossibleEnemies(db, player)

        var maiorId = 0  //Pega o inimigo com maior id
        for(i in possibleEnemies){
            if(i > maiorId) {
                maiorId = i
            }
        }

        assertEquals(maiorId, 24)

    }

    @Test
    fun testGetCommonEnemyData(){
        val random = 5
        val commonEnemyData = getCommonEnemyData(db, random)

        assertEquals(commonEnemyData[0], 140)
        assertEquals(commonEnemyData[1], 150)
        assertEquals(commonEnemyData[2], 8)
        assertEquals(commonEnemyData[3], 7)
        assertEquals(commonEnemyData[4], 0)
        assertEquals(commonEnemyData[5], 17)
        assertEquals(commonEnemyData[6], 0)
    }

    @Test
    fun testGetCommonEnemyName(){
        val random = 6
        val commonEnemyName = getCommonEnemyName(db, random)

        assertEquals(commonEnemyName, "Inimigo paia                  ")

    }

    @Test
    fun testGetCommonEnemyMovements(){
        val commonEnemyMovements = getCommonEnemyMovements(db, player)

        val menorId = 0  //Pega o movimento com maior id
        var posMenorId = 0
        for(i in 0..commonEnemyMovements.size){
            if(commonEnemyMovements[0].id > menorId) {
                posMenorId = i
            }
        }

        assertEquals(commonEnemyMovements[posMenorId].id, 31)
        assertEquals(commonEnemyMovements[posMenorId].hpown, -35)
        assertEquals(commonEnemyMovements[posMenorId].staminaown, 25)
    }

    @Test
    fun testGetPossibleMovementsId(){
        val movementsId = getPossibleMovementsId(db, player)

        var maiorId = 0  //Pega o movimento com maior id
        for(i in movementsId){
            if(i > maiorId) {
                maiorId = i
            }
        }

        assertEquals(maiorId, 31)
    }

    @Test
    fun testGetPossibleMovementsPrice(){
        val movementsPrice = getPossibleMovementsPrice(db, player)

        var maiorPreco = 0  //Pega o movimento com maior preco
        for(i in movementsPrice){
            if(i > maiorPreco) {
                maiorPreco = i
            }
        }

        assertEquals(maiorPreco, 30)
    }

    @Test
    fun testGetMovement(){
        val movement = getMovement(db, 1)

        assertEquals(movement.id, 1)
        assertEquals(movement.staminaown, -20)
        assertEquals(movement.hpenemy, -35)
    }

    @Test
    fun testBuyMovement(){
        val movement = Movement(0, "Ataque", 0, -50, 0, 0, 0, 0, -80,
            0, 0, 0, 0, 0, 0, 0, 60)
        var res = buyMovement(player, movement)

        assertEquals(res, 1)

        player.carteira += 60
        movement.price = 200
        res = buyMovement(player, movement)

        assertEquals(res, 0)
    }
}
