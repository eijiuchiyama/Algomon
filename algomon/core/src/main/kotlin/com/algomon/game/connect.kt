package algomon

import java.sql.*;

fun getConnection(){

    val jdbcUrl = "jdbc:postgresql://localhost:5432/example"

    val connection = DriverManager.getConnection(jdbcUrl, "postgres", "123369")

    if(connection.isValid(0))
        println("Conexão realizada")
}
