package com.algomon.game

import kotlinx.serialization.json.Json

suspend fun getPlayerData(): List<Int>{
    var playerData: List<Int> = emptyList()
    //val sql = "SELECT * FROM players WHERE id = 0;"
    val body = request("playerdata" , "*", "players", "id=0")
    playerData = Json.decodeFromString(body)
    return playerData
}

suspend fun getPlayerMovements(): MutableList<Movement>{
    var playermovements: MutableList<Movement>
    //val sql = "SELECT * FROM movements WHERE minlevel = 0;"
    val body = request("movementsdata" , "*", "movements", "minlevel=0")
    playermovements = Json.decodeFromString(body)
    return playermovements
}

suspend fun getSpecialEnemyData(countBattle: Int): List<Int>{
    var enemyData: List<Int> = emptyList()
    //val sql = "SELECT * FROM specialenemies WHERE level = $countBattle;"
    val body = request("enemydata" , "*", "specialenemies", "level=$countBattle")
    enemyData = Json.decodeFromString(body)
    return enemyData
}

suspend fun getSpecialEnemyName(countBattle: Int): String{
    var enemyName = ""
    //val sql = "SELECT name FROM specialenemies WHERE level = $countBattle;"
    val body = request("name" , "name", "specialenemies", "level=$countBattle")
    enemyName = Json.decodeFromString(body)
    return enemyName
}

suspend fun getSpecialEnemyMovements(enemyLevel: Int): MutableList<Movement>{
    var enemymovements: MutableList<Movement>
    //val sql = "SELECT * FROM movements WHERE minlevel <= $enemyLevel;"
    val body = request("movementsdata" , "*", "movements", "minlevel<=$enemyLevel")
    enemymovements = Json.decodeFromString(body)
    return enemymovements
}

suspend fun game(){

    val playerData = getPlayerData()
    //println(playerData)

    val playermovements = getPlayerMovements()

    val player = Player("Player", playerData[0], playerData[1], //Cria o player
        playermovements, playerData[2], playerData[3], playerData[4], playerData[5], 0, 0)

    var win = 1 //Ao perder, win = 0 e o jogador perde o jogo
    var countBattle = 0 //Conta as batalhas

    while(countBattle <= 1) { //Realiza um certo número de batalhas principais

        //Escolhe o oponente de specialenemies relativo à batalha atual do torneio
        val enemyData = getSpecialEnemyData(countBattle)
        val enemyName = getSpecialEnemyName(countBattle)
        val enemymovements = getSpecialEnemyMovements(enemyData[6])


        val enemy = Enemy(enemyName, enemyData[0], enemyData[1], enemymovements, enemyData[2], enemyData[3], enemyData[4],
            enemyData[5], enemyData[6])

        win = battle(player, enemy)

        if(win == 0 || win == 2){ //Se o player desistiu ou perdeu, ele perde o torneio
            println("Você perdeu uma batalha do torneio. Você foi eliminado")
            break
        } else{ //Se ele venceu, ele passa para a próxima fase
            println("Você venceu uma batalha do torneio! Você passou para a próxima fase!")
        }
        player.level++ //Level do player aumenta em 1 para ser possível aprender novos movimentos

        countBattle++

        //Começa o intervalo
        interval(player)
    }

    if(win == 0 || win == 2) println("Você perdeu o torneio. Mais sorte no próximo ano.")
    else println("Você venceu o torneio. Parabéns.")

    closeClient()

}


suspend fun main() {

	/**
     *Personagem
        1 - Nome
        2 - HP base
        3 - Stamina base
        4 - Atk base
        5 - Def base
        6 - Dodge base
        7 - Speed base

	 *Movimento
     	1 - ID
        2 - HP-P
    	3 - Stamina-P
    	4 - Atk-P
    	5 - Def-P
    	6 - Dodge-P
    	7 - Speed-P
        8 - HP-A
    	9 - Stamina-A
    	10 - Atk-A
    	11 - Def-A
    	12 - Dodge-A
    	13 - Speed-A
	*/

    /**
     * Ataque = Hacking
     * Regenerar HP = Take a nap
     * Regenerar Stamina = Recovery
     * Aumentar própria Defesa = Criptografar
     * Aumentar próprio Ataque = Correção de Bug
     * Aumentar próprio Speed = Compactar
     * Resetar os atributos próprios = Refactoring
     * Diminuir Defesa adversária = Descriptografar
     * Diminuir Ataque adversário = Destruir projeto
     * Diminuir o Dodge adversário = Path-finding
     */

    game() //Inicia um jogo
}
