package com.algomon.game

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class Connect(val db: String, val local: String, val porta: String, val banco: String, val user: String, val senha: String) {
    // GETs AND SETs
    var c: Connection? = null
    var statement: Statement? = null
    var strconexao: String? = null
    var driverjdbc: String? = null

    init {
        if (db == "PostgreSql") {
            strconexao = "jdbc:postgresql://$local:$porta/$banco"
            driverjdbc = "org.postgresql.Driver"
        }
    }

    //Conexão com o Banco de Dados
    fun connect(): Int {
        try {
            c = DriverManager.getConnection(strconexao, user, senha)
            statement = c!!.createStatement()
            println("Conexão realizada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Não foi possível se conectar ao banco de dados")
            return 0
        }
        return 1
    }

    fun disconnect(): Int {
        try {
            c!!.close()
            println("Desconexão realizada com sucesso")
        } catch (ex: SQLException) {
            System.err.println(ex)
            ex.printStackTrace()
            println("Não foi possível se desconectar do banco de dados")
            return 0
        }
        return 1
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
