package money

import kotlin.test.*

class MoneyTest {

    @Test
    fun `detect equal values`() {
        assertEquals(EUR(99), EUR(99))
        assertTrue(EUR(99).equivalent(EUR(99)))
    }

    @Test
    fun `detect currency differences`() {
        assertNotEquals(EUR(99), USD(99))
        assertFalse(EUR(99).equivalent(USD(99)))
    }

    @Test
    fun `detect amount differences`() {
        assertNotEquals(EUR(1), EUR(2))
        assertFalse(EUR(1).equivalent(EUR(2)))
    }

    @Test
    fun `multiplies correctly`() {
        assertEquals(
            EUR(400),
            EUR(200).multiplyAndRound(2.0)
        )
    }

    @Test
    fun `rounds up correctly`() {
        assertEquals(
            EUR(200),
            EUR(100).multiplyAndRound(1.999)
        )
    }

    @Test
    fun `rounds down correctly`() {
        assertEquals(
            EUR(199),
            EUR(100).multiplyAndRound(1.994)
        )
    }

    @Ignore
    @Test
    fun `exposes amount value`() {
        fail("Implement me")
    }

    @Ignore
    @Test
    fun `exposes currency value`() {
        fail("Implement me")
    }
}