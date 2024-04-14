package algomon

fun battle(player: Player, enemy: Enemy): Int{
    var turn = 1;

    println()
    println("Battle Start!!!")
    println()

    player.Show_Status()
    enemy.Show_Status()
    while (true) {

        println()
        println("Turn $turn")
        println()

        if (player.Speed > enemy.Speed) {
            player.ChooseMovement(enemy)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
            enemy.RandomMovement(player)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
        } else {
            enemy.RandomMovement(player)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if (player.HP == 0 || enemy.HP == 0) break;
            player.ChooseMovement(enemy)
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
