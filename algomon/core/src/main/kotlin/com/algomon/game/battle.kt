package algomon

fun battle(player: Player, enemy: Enemy, db: Connect): Int{
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

        //Se fugir, retorna 2. Se perder retorna 0 e se vencer retorna 1
        if (player.speed > enemy.speed) {
            if(player.ChooseMovement(enemy, db) == 0){
                return 2
            }
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.hp == 0 || enemy.hp == 0) break
            enemy.RandomMovement(player, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.hp == 0 || enemy.hp == 0) break
        } else {
            enemy.RandomMovement(player, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.hp == 0 || enemy.hp == 0) break
            player.ChooseMovement(enemy, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.hp == 0 || enemy.hp == 0) break
        }
        turn++
    }

    if (player.hp == 0){ //Perde a batalha
        return 0
    } else{ //Vence a batalha
        println("HP +3")
        println("Stamina +3")
        println("Atk +1")
        println("Def +1")
        println("Speed +1")
        player.hpbase += 3
        player.staminabase += 3
        player.atkbase += 1
        player.defbase += 1
        player.speedbase += 1

        player.carteira += enemy.hp/20
        return 1
    }
}
