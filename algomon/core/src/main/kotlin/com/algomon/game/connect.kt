package algomon

import java.sql.*

class Connect(bd: String, local: String, porta: String, banco: String, user: String, senha: String) {
    // GETs AND SETs
    var local: String? = null
    var user: String? = null
    var senha: String? = null
    var c: Connection? = null
    var statement: Statement? = null
    var str_conexao: String? = null
    var driverjdbc: String? = null

    init {
        if (bd == "PostgreSql") {
            str_conexao = "jdbc:postgresql://$local:$porta/$banco"
            this.local = local
            this.senha = senha
            this.user = user
            driverjdbc = "org.postgresql.Driver"
        }
    }

    //Conexão com o Banco de Dados
    fun connect() {
        try {
            c = DriverManager.getConnection(str_conexao, user, senha)
            println("Conexão realizada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Não foi possível se conectar ao banco de dados")
        }
    }

    fun disconnect() {
        try {
            c!!.close()
            println("Desconexão realizada com sucesso")
        } catch (ex: SQLException) {
            System.err.println(ex)
            ex.printStackTrace()
            println("Não foi possível se desconectar do banco de dados")
        }
    }

    fun query(query: String?): ResultSet? {
        return try {
            statement!!.executeQuery(query)
        } catch (ex: SQLException) {
            ex.printStackTrace()
            null
        }
    }
}

fun databaseConnect(): Connect{
    val c = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")
    c.connect()
    return c
}

fun databaseDisconnect(c: Connect){
    c.disconnect()
}
