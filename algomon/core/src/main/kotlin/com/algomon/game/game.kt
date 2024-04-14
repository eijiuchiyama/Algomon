package algomon

fun game(player: Player, enemy: Enemy){
    start()

    var turn = 1;

    println()
    println("Battle Start!!!")
    println()

    player.Show_Status()
    enemy.Show_Status()
    while(true){

        println()
        println("Turn $turn")
        println()

        if(player.Speed > enemy.Speed){
            player.ChooseMovement(enemy)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if(player.HP==0 || enemy.HP==0) break;
            enemy.RandomMovement(player)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if(player.HP==0 || enemy.HP==0) break;
        }
        else{
            enemy.RandomMovement(player)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if(player.HP==0 || enemy.HP==0) break;
            player.ChooseMovement(enemy)
            player.Show_Status()
            enemy.Show_Status()
            println()
            if(player.HP==0 || enemy.HP==0) break;
        }
        turn++
    }
    if(player.HP==0) println("Game Over")
    else if(enemy.HP==0) println("You Win")
    else println("Draw")
}


fun main() {
	/**
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
     * Regenerar HP e Stamina = take a nap
     * Aumentar própria Defesa = criptografar
     * Aumentar próprio Ataque = Correção de Bug/
     * Aumentar próprio Speed = Compactar
     * Resetar os atributos próprios = Refactoring
     * Diminuir Defesa adversária = descriptografar
     * Diminuir Ataque adversário =
     * Diminuir o Dodge adversári = Path-finding
     */
    var Jogador = Player("Test", 500,500,listOf(1,2,3),10,10,10,10)
    var ABC = Enemy("ABC", 500,500,listOf(1,2,3),50,50,50,50)
    game(Jogador,ABC)
}
