package algomon

fun game(){
    var db = databaseConnect() //Realiza a conexão entre o jogo e o banco de dados
    if(start() == 0) //Inicia o jogo, apresentando o contexto
        return

    var sql = "SELECT * FROM players WHERE id = 0;"
    var rs = db.query(sql)
    while(rs!!.next()){
        println("HP: " + rs.getInt("basehp"))
        println("Stamina: " + rs.getInt("basestamina"))
        println("Atk: " + rs.getInt("baseatk"))
        println("Def: " + rs.getInt("basedef"))
        println("Dodge: " + rs.getString("basedodge"))
        println("Speed: " + rs.getString("basespeed"))
    }
    var player = Player("Test", 500,500,listOf(1,2,3),10,10,10,10)

    var win = 1 //Ao perder, win = 0 e o jogador perde o jogo
    var battle = 0 //Conta as batalhas

    while(battle <= 7) { //Realiza um certo número de batalhas principais
        //Escolhe o oponenete aleatoriamente
        var enemy = Enemy("ABC", 500,500,listOf(1,2,3),50,50,50,50)
        win = battle(player, enemy)
        if(win == 0 || win == 2) break

        battle++

        win = interval(player)
        if(win == 0) break
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
     * Regenerar HP e Stamina = take a nap
     * Aumentar própria Defesa = criptografar
     * Aumentar próprio Ataque = Correção de Bug
     * Aumentar próprio Speed = Compactar
     * Resetar os atributos próprios = Refactoring
     * Diminuir Defesa adversária = descriptografar
     * Diminuir Ataque adversário =
     * Diminuir o Dodge adversário = Path-finding
     */

    game()
}
