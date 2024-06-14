package com.algomon.game

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TestInterval{
    var skills = mutableListOf<Movement>()
    var move = Movement(0, "ataque", 0, -50, 0, 0, 0, 0, -100,
        0, 0, 0, 0, 0, 0, 100, 0)
    val player = Player("Pedrinho", 50, 70, skills, 40, 40, 5, 40, 3, 100)

    init{
        skills.add(move)
    }

    @Test
    fun testGetPossibleEnemies() = runTest{
        val possibleEnemies = getPossibleEnemies(player)

        var maiorId = 0  //Pega o inimigo com maior id
        for(i in possibleEnemies){
            if(i > maiorId) {
                maiorId = i
            }
        }

        assertEquals(maiorId, 24)

    }

    @Test
    fun testGetCommonEnemyData() = runTest{
        val random = 5
        val commonEnemyData = getCommonEnemyData(random)

        assertEquals(commonEnemyData[0], 140)
        assertEquals(commonEnemyData[1], 150)
        assertEquals(commonEnemyData[2], 8)
        assertEquals(commonEnemyData[3], 7)
        assertEquals(commonEnemyData[4], 0)
        assertEquals(commonEnemyData[5], 17)
    }

    @Test
    fun testGetCommonEnemyName() = runTest{
        val random = 6
        val commonEnemyName = getCommonEnemyName(random)

        assertEquals(commonEnemyName, "Inimigo paia")

    }

    @Test
    fun testGetCommonEnemyMovements() = runTest{
        val commonEnemyMovements = getCommonEnemyMovements(player)
        println(commonEnemyMovements.size)

        var maiorId = 0  //Pega o movimento com maior id
        var posMaiorId = 0
        for(i in 0..commonEnemyMovements.size - 1){
            if(commonEnemyMovements[i].id > maiorId) {
                maiorId = commonEnemyMovements[i].id
                posMaiorId = i
            }
        }

        assertEquals(commonEnemyMovements[posMaiorId].id, 31)
        assertEquals(commonEnemyMovements[posMaiorId].hpown, -35)
        assertEquals(commonEnemyMovements[posMaiorId].staminaown, 25)
    }

    @Test
    fun testGetPossibleMovementsId() = runTest{
        val movementsId = getPossibleMovementsId(player)

        var maiorId = 0  //Pega o movimento com maior id
        for(i in movementsId){
            if(i > maiorId) {
                maiorId = i
            }
        }

        assertEquals(maiorId, 31)
    }

    @Test
    fun testGetPossibleMovementsPrice() = runTest{
        val movementsPrice = getPossibleMovementsPrice(player)

        var maiorPreco = 0  //Pega o movimento com maior preco
        for(i in movementsPrice){
            if(i > maiorPreco) {
                maiorPreco = i
            }
        }

        assertEquals(maiorPreco, 30)
    }

    @Test
    fun testGetMovement() = runTest{
        val movement = getMovement(1)

        assertEquals(movement.id, 1)
        assertEquals(movement.staminaown, -20)
        assertEquals(movement.hpenemy, -35)
    }

    @Test
    fun testBuyMovement(){
        val movement = Movement(0, "Ataque", 0, -50, 0, 0, 0, 0, -80,
            0, 0, 0, 0, 0, 0, 0, 60)
        var res = buyMovement(player, movement, player.skills.size == 6, 0)

        assertEquals(res, 1)

        player.carteira += 60
        movement.price = 200
        res = buyMovement(player, movement, player.skills.size == 6, 0)

        assertEquals(res, 0)
    }
}
