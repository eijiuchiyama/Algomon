package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestEnemy {

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, sum(40, 2))
    }

    @Test
    fun testConnect(){
        val c = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")
        assertEquals(1, c.connect())
    }

    @Test
    fun testDisconnect(){
        val c = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "123369")
        val d = Connect("PostgreSql", "localhost", "5432", "postgres", "postgres", "111111")
        c.connect()
        d.connect()
        assertEquals(1, c.disconnect())
        assertEquals(0, d.disconnect())
    }


}
