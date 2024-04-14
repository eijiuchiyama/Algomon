package algomon

fun game(player: Player, enemy: Enemy){
    start() //Inicia o jogo, apresentando o contexto

    var win = 1 //Ao perder, win = 0 e o jogador perde o jogo
    var battle = 0 //Conta as batalhas

    while(battle <= 7) { //Realiza um certo número de batalhas principais
        //Escolhe o oponenete aleatoriamente
        win = battle(player, enemy)
        if(win == 0) break

        battle++

        win = interval(player)
        if(win == 0) break
    }

    if(win == 0) println("Você perdeu o torneio. Mais sorte no próximo ano.")
    else println("Você venceu o torneio. Parabéns.")

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
