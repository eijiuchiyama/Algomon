package com.algomon.game

fun getPlayerData(db: Connect): List<Int>{
    var playerData: List<Int> = emptyList()
    val sql = "SELECT * FROM players WHERE id = 0;"
    val rs = db.query(sql)

    while(rs!!.next()){
        playerData = playerData + rs.getInt("basehp")
        playerData = playerData + rs.getInt("basestamina")
        playerData = playerData + rs.getInt("baseatk")
        playerData = playerData + rs.getInt("basedef")
        playerData = playerData + rs.getInt("basedodge")
        playerData = playerData + rs.getInt("basespeed")
    }
    return playerData
}

fun getPlayerMovements(db: Connect): List<Movement>{
    var playermovements: List<Movement> = emptyList()
    val sql = "SELECT id FROM movements WHERE minlevel = 0;"
    val rs = db.query(sql)
    while(rs!!.next()){
        playermovements = playermovements + Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
            rs.getInt("baseaccuracy"), rs.getInt("price"))
    }
    return playermovements
}

fun getSpecialEnemyData(db: Connect, countBattle: Int): List<Int>{
    var enemyData: List<Int> = emptyList()
    val sql = "SELECT * FROM specialenemies WHERE level = $countBattle;"
    val rs = db.query(sql)

    while(rs!!.next()){
        enemyData = enemyData + rs.getInt("basehp")
        enemyData = enemyData + rs.getInt("basestamina")
        enemyData = enemyData + rs.getInt("baseatk")
        enemyData = enemyData + rs.getInt("basedef")
        enemyData = enemyData + rs.getInt("basedodge")
        enemyData = enemyData + rs.getInt("basespeed")
        enemyData = enemyData + rs.getInt("level")
    }
    return enemyData
}

fun getSpecialEnemyName(db: Connect, countBattle: Int): String{
    var enemyName = ""
    val sql = "SELECT * FROM specialenemies WHERE level = $countBattle;"
    val rs = db.query(sql)

    while(rs!!.next()){
        enemyName = rs.getString("name")
    }
    return enemyName
}

fun getSpecialEnemyMovements(db: Connect, enemyLevel: Int): List<Movement>{
    var enemymovements: List<Movement> = emptyList()
    val sql = "SELECT id FROM movements WHERE minlevel <= ${enemyLevel};"
    val rs = db.query(sql)
    while(rs!!.next()){
        enemymovements = enemymovements + Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
            rs.getInt("baseaccuracy"), rs.getInt("price"))
    }
    return enemymovements
}

fun game(){
    val db = databaseConnect() //Realiza a conexão entre o jogo e o banco de dados
    if(start() == 0) //Inicia o jogo, apresentando o contexto
        return

    var playerData = getPlayerData(db)

    var playermovements = getPlayerMovements(db)

    val player = Player("Player", playerData[0], playerData[1], //Cria o player
        playermovements, playerData[2], playerData[3], playerData[4], playerData[5], 0, 0)

    var win = 1 //Ao perder, win = 0 e o jogador perde o jogo
    var countBattle = 0 //Conta as batalhas

    while(countBattle <= 1) { //Realiza um certo número de batalhas principais

        //Escolhe o oponente de specialenemies relativo à batalha atual do torneio
        val enemyData = getSpecialEnemyData(db, countBattle)
        val enemyName = getSpecialEnemyName(db, countBattle)
        val enemymovements = getSpecialEnemyMovements(db, enemyData[6])


        val enemy = Enemy(enemyName, enemyData[0], enemyData[1], enemymovements, enemyData[2], enemyData[3], enemyData[4],
            enemyData[5], enemyData[6])

        win = battle(player, enemy)

        if(win == 0 || win == 2){ //Se o player desistiu ou perdeu, ele perde o torneio
            println("Você perdeu uma batalha do torneio. Você foi eliminado")
            break
        } else{ //Se ele venceu, ele passa para a próxima fase
            println("Você venceu uma batalha do torneio! Você passou para a próxima fase!")
        }

        countBattle++

        //Começa o intervalo
        interval(player, db)
    }

    if(win == 0 || win == 2) println("Você perdeu o torneio. Mais sorte no próximo ano.")
    else println("Você venceu o torneio. Parabéns.")

    databaseDisconnect(db)

}


fun main() {

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
