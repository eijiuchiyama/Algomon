abstract class Random

open class character{
    var name: String = ""
    var HP: Int = 100
    var Stamina: Int = 100
    var Skill = arrayOf(1, 2)
    var Atk = 10
    var Def = 10
    var Dodge = 10
    var Speed = 10
}

class enemy: character(){
    fun movimento(){
        //var number = Random.nextInt(0, sizeof(Skill))
    }
}

fun main() {

    /**
     * Ataque = Hacking
     * Regenerar HP e Stamina = take a nap
     * Aumentar própria Defesa = criptografar
     * Aumentar própria Ataque = Correção de Bug
     * Aumentar próprio Speed = Compactar
     * Resetar os atributos próprios = Refactoring
     * Diminuir Defesa adversária = descriptografar
     * Diminuir Ataque adversária = 
     * Diminuir o Dodge adversária = Path-finding
     */

}
