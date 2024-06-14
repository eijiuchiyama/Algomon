package com.algomon.game

import kotlinx.serialization.json.Json
import java.util.Scanner

suspend fun getPossibleEnemies(player: Player): List<Int>{
    var enemiesId: List<Int> = emptyList()
    //val sql = "SELECT id FROM commonenemies WHERE level = ${player.level};"
    val body = request("ids" , "id", "commonenemies", "level=${player.level}")
    enemiesId = Json.decodeFromString(body)
    return enemiesId
}

fun getRandom(possibleEnemiesId: List<Int>): Int{
    return possibleEnemiesId[kotlin.random.Random.nextInt(0, possibleEnemiesId.size)]
}

suspend fun getCommonEnemyData(random: Int): List<Int>{
    var enemyData: List<Int> = emptyList()
    //val sql = "SELECT * FROM commonenemies WHERE id = $random"
    val body = request("enemydata" , "*", "commonenemies", "id=$random")
    enemyData = Json.decodeFromString(body)
    return enemyData
}

suspend fun getCommonEnemyName(random: Int): String{
    var enemyName = ""
    //val sql = "SELECT name FROM commonenemies WHERE id = $random"
    val body = request("name" , "name", "commonenemies", "id=$random")
    enemyName = Json.decodeFromString(body)
    return enemyName
}

suspend fun getCommonEnemyMovements(player: Player): MutableList<Movement>{
    var enemyMovements: MutableList<Movement>
    //val sql = "SELECT * FROM movements WHERE minlevel <= ${player.level}"
    val body = request("movementsdata" , "*", "movements", "minlevel<=${player.level}")
    enemyMovements = Json.decodeFromString(body)
    return enemyMovements
}

suspend fun training(player: Player){

    //Escolhe o oponente de commonenemies aleatoriamente
    val possibleEnemiesId = getPossibleEnemies(player)
    val random = getRandom(possibleEnemiesId)

    val enemyData = getCommonEnemyData(random)
    val enemyName = getCommonEnemyName(random)
    val enemymovements = getCommonEnemyMovements(player)

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

suspend fun getPossibleMovementsId(player: Player): List<Int>{
    var movimentosDisponiveisId: List<Int> = emptyList()
    val body = request("movementsdata", "*", "movements", "minlevel<=${player.level}")
    val movementsId: List<Movement> = Json.decodeFromString(body)
    for(i in 0..movementsId.size - 1){
        var found = 0
        for (action in player.skills){
            if(action.id == movementsId[i].id){
                found = 1
                break
            }
        }
        if(found == 0)
            movimentosDisponiveisId += movementsId[i].id
    }
    return movimentosDisponiveisId
}

suspend fun getPossibleMovementsName(player: Player): List<String>{
    var movimentosDisponiveisName: List<String> = emptyList()
    val body = request("names", "name", "movements", "minlevel<=${player.level}")
    val movementsName: List<String> = Json.decodeFromString(body)
    for(i in 0..movementsName.size - 1){
        var found = 0
        for (action in player.skills){
            if(action.name == movementsName[i]){
                found = 1
                break
            }
        }
        if(found == 0)
            movimentosDisponiveisName += movementsName[i]
    }
    return movimentosDisponiveisName
}

suspend fun getPossibleMovementsPrice(player: Player): List<Int>{
    var movimentosDisponiveisPrice: List<Int> = emptyList()
    val body = request("movementsdata", "*", "movements", "minlevel<=${player.level}")
    val movementsId: List<Movement> = Json.decodeFromString(body)
    for(i in 0..movementsId.size - 1){
        var found = 0
        for (action in player.skills){
            if(action.id == movementsId[i].id){
                found = 1
                break
            }
        }
        if(found == 0)
            movimentosDisponiveisPrice += movementsId[i].price
    }
    return movimentosDisponiveisPrice
}

suspend fun getMovement(choose: Int): Movement{
    var movimento = Movement(0, "", 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0)
    //val sql = "SELECT * FROM movements WHERE id = $choose;"
    val body = request("movementdata", "*", "movements", "id=$choose")
    movimento = Json.decodeFromString(body)
    return movimento
}

fun buyMovement(player: Player, movement: Movement, remove:Boolean, removed:Int): Int{ //Retorna 1 se foi possível comprar e 0 se não for possível
    if (movement.price <= player.carteira) {
        if (remove) {
            player.skills.removeAt(removed)
            player.skills.add(movement)
            player.carteira -= movement.price
            return 1
        } else {
            player.skills += movement
            player.carteira -= movement.price
            return 1

        }
    }
    return 0
}

suspend fun getNewMovement(player: Player){
    println("Qual movimento você deseja obter?")
    val movimentosDisponiveisId = getPossibleMovementsId(player)
    val movimentosDisponiveisName = getPossibleMovementsName(player)
    val movimentosDisponiveisPrice = getPossibleMovementsPrice(player)

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

    val movement = getMovement(choose)

    var remove = false
    var removed = 0
    if (player.skills.size == 6) {
        remove = true
        println("Remova um movimento para adquirir um novo")
        removed = Scanner(System.`in`).nextLine().toInt()
    }

    if(buyMovement(player, movement, remove, removed) == 1){
        println("Movimento adicionado à sua lista de movimentos")
    } else{
        println("Você não tem dinheiro para adquirir o movimento")
    }
}

suspend fun interval(player: Player){
    while(true) {

        println("Quer descansar, treinar ou aprender novo movimento? (d/t/n)")
        val choice = Scanner(System.`in`).nextLine()

        if(choice == "d" || choice == "D"){ //Caso escolhe descansar

            println("Você saiu do treinamento. Vamos então para o próximo turno.")
            break

        } else if(choice == "t" || choice == "T") { //Caso escolhe treinar

            training(player)

        } else{ //Caso escolhe obter movimento

            getNewMovement(player)

        }
    }
    return
}
