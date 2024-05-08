package com.algomon.game

import org.junit.Assert.assertEquals
import org.junit.Test

internal class Test {

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, sum(40, 2))
    }


}
