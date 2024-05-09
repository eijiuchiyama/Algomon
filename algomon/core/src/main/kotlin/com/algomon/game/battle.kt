package com.algomon.game

fun turn(player:Player, enemy: Enemy): Int{ //Retorna 2 se o jogador desistir, 1 se a batalha continuar após o turno e 0 se ela acabar
    if (player.speed > enemy.speed) {
        if(player.ChooseMovement(enemy) == 0){
            return 2
        }
        player.Show_Status()
        enemy.Show_Status()
        println()
        if (player.hp == 0 || enemy.hp == 0) return 0
        enemy.RandomMovement(player)
        player.Show_Status()
        enemy.Show_Status()
        println()
        if (player.hp == 0 || enemy.hp == 0) return 0
    } else {
        enemy.RandomMovement(player)
        player.Show_Status()
        enemy.Show_Status()
        println()
        if (player.hp == 0 || enemy.hp == 0) return 0
        player.ChooseMovement(enemy)
        player.Show_Status()
        enemy.Show_Status()
        println()
        if (player.hp == 0 || enemy.hp == 0) return 0
    }
    return 1
}

fun updatePlayerData(player: Player, enemy: Enemy){
    player.hpbase += 3
    player.staminabase += 3
    player.atkbase += 1
    player.defbase += 1
    player.speedbase += 1
    player.carteira += enemy.hp/20
}

fun battle(player: Player, enemy: Enemy): Int{ //Retorna 2 se o jogador desistiu, 1 se ele venceu e 0 se ele perdeu a batalha
    var turn = 1

    println()
    println("Battle Start!!!")
    println()

    player.ResetStats()

    player.Show_Status()
    enemy.Show_Status()

    while (true) {

        println()
        println("Turno $turn")
        println()

        val res = turn(player, enemy)
        if(res == 0) break;
        if(res == 2) return 2;
        turn++
    }

    if (player.hp == 0){ //O jogador perdeu a batalha
        return 0
    } else{ //O jogador venceu a batalha
        println("HP +3")
        println("Stamina +3")
        println("Atk +1")
        println("Def +1")
        println("Speed +1")
        updatePlayerData(player, enemy)
        return 1
    }
}
