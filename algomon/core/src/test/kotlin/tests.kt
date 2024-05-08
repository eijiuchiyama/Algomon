import org.junit.Assert.assertEquals
import org.junit.Test

fun sum(a:Int, b:Int):Int{
    return a + b
}

internal class Test {

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, sum(40, 2))
    }
}
