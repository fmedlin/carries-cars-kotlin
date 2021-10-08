package `pricing-engine`

import money.EUR
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class PricingEngineTest {

    @Test
    fun `calculates price per minute`() {
        // Given
        val pricePerMinute = EUR(30)
        val duration = pricingEngine.durationInMinutes(1)

        // When
        val calculatedPrice = pricingEngine.calculatePrice(pricePerMinute, duration)

        // Then
        assertEquals(EUR(30), calculatedPrice)
    }

    @Test
    fun `guards against 0 or negative duration`() {
        assertThrows<IllegalArgumentException> {
            pricingEngine.durationInMinutes(0)
        }
    }

    @Test
    fun `duration verifies valid input`() {
        // Given
        val input = UnverifiedDuration(1)
        val expected = input.verify()

        // Then
        assertEquals(expected, pricingEngine.durationInMinutes(1))
    }

    @Test
    fun `duration throws error for invalid input`() {
        assertThrows<IllegalArgumentException> {
            UnverifiedDuration(0).verify()
        }
    }
}