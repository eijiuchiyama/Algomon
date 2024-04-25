package algomon

fun game(){
    val db = databaseConnect() //Realiza a conexão entre o jogo e o banco de dados
    if(start() == 0) //Inicia o jogo, apresentando o contexto
        return

    var sql = "SELECT * FROM players WHERE id = 0;"
    var rs = db.query(sql)
    var playerhp = 0
    var playerstamina = 0
    var playeratk = 0
    var playerdef = 0
    var playerdodge = 0
    var playerspeed = 0
    while(rs!!.next()){
        playerhp = rs.getInt("basehp")
        playerstamina = rs.getInt("basestamina")
        playeratk = rs.getInt("baseatk")
        playerdef = rs.getInt("basedef")
        playerdodge = rs.getInt("basedodge")
        playerspeed = rs.getInt("basespeed")
    }

    var playermovements: List<Int> = emptyList()
    sql = "SELECT id FROM movements WHERE minlevel = 0;"
    rs = db.query(sql)
    while(rs!!.next()){
        playermovements = playermovements + rs.getInt("id")
    }

    val player = Player("Player", playerhp, playerstamina,
        playermovements, playeratk, playerdef, playerdodge, playerspeed, 0, 0)

    var win = 1 //Ao perder, win = 0 e o jogador perde o jogo
    var countBattle = 0 //Conta as batalhas

    while(countBattle <= 1) { //Realiza um certo número de batalhas principais

        //Escolhe o oponente de specialenemies relativo à batalha atual do torneio
        sql = "SELECT * FROM specialenemies WHERE level = $countBattle;"
        rs = db.query(sql)

        var enemyname = ""
        var enemyhp = 0
        var enemystamina = 0
        var enemyatk = 0
        var enemydef = 0
        var enemydodge = 0
        var enemyspeed = 0
        val enemylevel = 0
        var enemymovements: List<Int> = emptyList()
        while(rs!!.next()){
            enemyname = rs.getString("name")
            enemyhp = rs.getInt("basehp")
            enemystamina = rs.getInt("basestamina")
            enemyatk = rs.getInt("baseatk")
            enemydef = rs.getInt("basedef")
            enemydodge = rs.getInt("basedodge")
            enemyspeed = rs.getInt("basespeed")
        }

        sql = "SELECT id FROM movements WHERE minlevel <= ${enemylevel};"
        rs = db.query(sql)
        while(rs!!.next()){
            enemymovements = enemymovements + rs.getInt("id")
        }

        val enemy = Enemy(enemyname, enemyhp, enemystamina, enemymovements, enemyatk, enemydef, enemydodge, enemyspeed, enemylevel)
        win = battle(player, enemy, db)
        if(win == 0 || win == 2){
            println("Você perdeu uma batalha do torneio. Você foi eliminado")
            break
        } else{
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

    game()
}
