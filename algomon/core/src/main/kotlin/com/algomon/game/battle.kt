package algomon

fun battle(player: Player, enemy: Enemy, db: Connect): Int{
    var turn = 1;

    println()
    println("Battle Start!!!")
    println()

    player.Show_Status()
    enemy.Show_Status()
    while (true) {

        println()
        println("Turno $turn")
        println()

        //Se fugir, retorna 2. Se perder retorna 0 e se vencer retorna 1
        if (player.Speed > enemy.Speed) {
            if(player.ChooseMovement(enemy, db) == 0){
                return 2
            }
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
            enemy.RandomMovement(player, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
        } else {
            enemy.RandomMovement(player, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
            player.ChooseMovement(enemy, db)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
        }
        turn++
    }

    if (player.HP == 0){
        println("Game Over")
        return 0
    }
    else if (enemy.HP == 0){
        println("You Win")
        return 1
    }
    else{
        println("Draw")
        return 1
    }
}
