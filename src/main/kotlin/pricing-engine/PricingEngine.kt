package `pricing-engine`

import money.Money
import kotlin.jvm.Throws

interface Duration {
    val durationInMinutes: Int
}

private data class VerifiedDuration(override val durationInMinutes: Int): Duration

/**
 * UnverifiedDuration should be used when accepting input from untrusted sources (pretty much anywhere) in the model.
 * This type models input that has not been verified and is therefore unsafe to use until it has been verified.
 * Use Verify() to transform it to trusted input in the form of a duration model.
 *
 *  throws IllegalArgumentException when @durationInMinutes <= 0
 */
data class UnverifiedDuration(override val durationInMinutes: Int): Duration {
    fun verify(): Duration {
        if (durationInMinutes <= 0) {
            throw IllegalArgumentException("Duration should be a positive number in minutes")
        }
        return VerifiedDuration(durationInMinutes)
    }
}

interface PricingEngine {
    fun calculatePrice(pricePerMinute: Money, duration: Duration): Money
    fun durationInMinutes(minutes: Int): Duration
}

/**
 *
 *  throws IllegalArgumentException when duration is unverified
 */
val pricingEngine = object : PricingEngine {
    override fun calculatePrice(pricePerMinute: Money, duration: Duration): Money {
        if (duration is UnverifiedDuration) {
            throw IllegalArgumentException("Duration must be verified")
        }
        val durationInMinutes = duration.durationInMinutes.toDouble()
        return pricePerMinute.multiplyAndRound(durationInMinutes)
    }

    override fun durationInMinutes(minutes: Int): Duration {
        return UnverifiedDuration(minutes).verify()
    }
}