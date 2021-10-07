package `pricing-engine`

import money.Money

interface Duration {
    val durationInMinutes: Int
}

data class VerifiedDuration(override val durationInMinutes: Int): Duration

data class UnverifiedDuration(override val durationInMinutes: Int): Duration {
    fun verify(): VerifiedDuration {
        TODO("Implement me")
    }
}

interface PricingEngine {
    fun calculatePrice(pricePerMinute: Money, duration: Duration)
    fun durationInMinutes(minutes: Int)
}
