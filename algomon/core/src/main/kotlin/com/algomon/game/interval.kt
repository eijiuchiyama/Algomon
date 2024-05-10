package com.algomon.game

import java.util.Scanner

fun getPossibleEnemies(db: Connect, player: Player): List<Int>{
    var enemiesId: List<Int> = emptyList()
    val sql = "SELECT * FROM commonenemies WHERE level = ${player.level};"
    val rs = db.query(sql)
    while(rs!!.next()){
        enemiesId = enemiesId + rs.getInt("id")
    }
    return enemiesId
}

fun getRandom(possibleEnemiesId: List<Int>): Int{
    return possibleEnemiesId[kotlin.random.Random.nextInt(0, possibleEnemiesId.size)]
}

fun getCommonEnemyData(db: Connect, random: Int): List<Int>{
    var enemyData: List<Int> = emptyList()
    val sql = "SELECT * FROM commonenemies WHERE id = $random"
    val rs = db.query(sql)
    while(rs!!.next()){
        enemyData = enemyData + rs.getInt("basehp")
        enemyData = enemyData + rs.getInt("basestamina")
        enemyData = enemyData + rs.getInt("baseatk")
        enemyData = enemyData + rs.getInt("basedef")
        enemyData = enemyData + rs.getInt("basedodge")
        enemyData = enemyData + rs.getInt("basespeed")
    }
    return enemyData
}

fun getCommonEnemyName(db: Connect, random: Int): String{
    var enemyName = ""
    val sql = "SELECT * FROM commonenemies WHERE id = $random"
    val rs = db.query(sql)
    while(rs!!.next()){
        enemyName = rs.getString("name")
    }
    return enemyName
}

fun getCommonEnemyMovements(db: Connect, player: Player): List<Movement>{
    var enemyMovements: List<Movement> = emptyList()
    val sql = "SELECT id FROM movements WHERE minlevel <= ${player.level}"
    val rs = db.query(sql)
    while(rs!!.next()){
        enemyMovements = enemyMovements + Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
            rs.getInt("baseaccuracy"), rs.getInt("price"))
    }
    return enemyMovements
}

fun training(db: Connect, player: Player){

    //Escolhe o oponente de commonenemies aleatoriamente
    val possibleEnemiesId = getPossibleEnemies(db, player)
    val random = getRandom(possibleEnemiesId)

    val enemyData = getCommonEnemyData(db, random)
    val enemyName = getCommonEnemyName(db, random)
    val enemymovements = getCommonEnemyMovements(db, player)

    val enemy = Enemy(enemyName, enemyData[0], enemyData[1], enemymovements, enemyData[2], enemyData[3], enemyData[4], enemyData[5], player.level)
    val res = battle(player, enemy)
    if(res == 0) {
        println("Você perdeu a batalha. Mais cuidado na próxima")
    } else if(res == 1){
        println("Bom jogo. Você venceu a batalha!")
    } else{
        println("Você desistiu da batalha. Mas não se preocupe. Este é somente um treino")
    }
}

fun getPossibleMovementsId(db: Connect, player: Player): List<Int>{
    var movimentosDisponiveis: List<Int> = emptyList()
    val sql = "SELECT * FROM movements WHERE minlevel <= ${player.level};"
    val rs = db.query(sql)
    while(rs!!.next()){
        for(action in player.skills){
            if(rs.getInt("id") == action.id)
                continue
        }
        movimentosDisponiveis = movimentosDisponiveis + rs.getInt("id")
    }
    return movimentosDisponiveis
}

fun getPossibleMovementsName(db: Connect, player: Player): List<String>{
    var movimentosDisponiveis: List<String> = emptyList()
    val sql = "SELECT * FROM movements WHERE minlevel <= ${player.level};"
    val rs = db.query(sql)
    while(rs!!.next()){
        for(action in player.skills){
            if(rs.getInt("id") == action.id)
                continue
        }
        movimentosDisponiveis = movimentosDisponiveis + rs.getString("name")
    }
    return movimentosDisponiveis
}

fun getPossibleMovementsPrice(db: Connect, player: Player): List<Int>{
    var movimentosDisponiveis: List<Int> = emptyList()
    val sql = "SELECT * FROM movements WHERE minlevel <= ${player.level};"
    val rs = db.query(sql)
    while(rs!!.next()){
        for(action in player.skills){
            if(rs.getInt("id") == action.id)
                continue
        }
        movimentosDisponiveis = movimentosDisponiveis + rs.getInt("preco")
    }
    return movimentosDisponiveis
}

fun getMovement(db: Connect, choose: Int): Movement{
    var movimento = Movement(0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0)
    val sql = "SELECT * FROM movements WHERE id = $choose;"
    val rs = db.query(sql)
    while(rs!!.next()){
        movimento = Movement(rs.getInt("id"), rs.getString("name"), rs.getInt("hpown"), rs.getInt("staminaown"),
            rs.getInt("atkown"), rs.getInt("defown"), rs.getInt("dodgeown"), rs.getInt("speedown"), rs.getInt("hpenemy"), rs.getInt("staminaenemy"),
            rs.getInt("atkenemy"), rs.getInt("defenemy"), rs.getInt("dodgeenemy"), rs.getInt("speedenemy"), rs.getInt("minlevel"),
            rs.getInt("baseaccuracy"), rs.getInt("price"))
    }
    return movimento
}

fun buyMovement(player: Player, movement: Movement): Int{ //Retorna 1 se foi possível comprar e 0 se não for possível
    if(movement.price <= player.carteira){
        player.skills += movement
        player.carteira -= movement.price
        return 1
    }
    return 0
}

fun getNewMovement(db: Connect, player: Player){
    println("Qual movimento você deseja obter?")
    val movimentosDisponiveisId = getPossibleMovementsId(db, player)
    val movimentosDisponiveisName = getPossibleMovementsName(db, player)
    val movimentosDisponiveisPrice = getPossibleMovementsPrice(db, player)

    val cont = 0
    for(i in movimentosDisponiveisId){
        println("Id: $i Price: ${movimentosDisponiveisPrice[cont]} Name: ${movimentosDisponiveisName[cont]}")
    }

    println("Escolha seu movimento:")
    val choose = Scanner(System.`in`).nextInt()
    if(choose !in movimentosDisponiveisId){
        println("Movimento não disponível.")
        return
    }

    val movement = getMovement(db, choose)

    if(buyMovement(player, movement) == 1){
        println("Movimento adicionado à sua lista de movimentos")
    } else{
        println("Você não tem dinheiro para adquirir o movimento")
    }
}

fun interval(player: Player, db:Connect){
    while(true) {

        println("Quer descansar, treinar ou aprender novo movimento? (d/t/n)")
        val choice = Scanner(System.`in`).nextLine()

        if(choice == "d" || choice == "D"){ //Caso escolhe descansar

            println("Você saiu do treinamento. Vamos então para o próximo turno.")
            break

        } else if(choice == "t" || choice == "T") { //Caso escolhe treinar

            training(db, player)

        } else{ //Caso escolhe obter movimento

            getNewMovement(db, player)

        }
    }
    return
}
