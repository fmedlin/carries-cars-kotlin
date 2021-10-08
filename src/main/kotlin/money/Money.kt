package money

import kotlin.math.roundToInt

typealias CurrencyIsoCode = String

interface Money {
    fun amount(): Int
    fun currencyIsoCode(): CurrencyIsoCode
    fun multiplyAndRound(multiplier: Double): Money
    fun equivalent(other: Money): Boolean
}

fun EUR(amount: Int): Money = TrustedMoney(amount, "EUR")
fun USD(amount: Int): Money = TrustedMoney(amount, "USD")

private data class TrustedMoney(
    private val amount: Int,
    private val currencyIsoCode: CurrencyIsoCode
) : Money {
    override fun amount(): Int = amount
    override fun currencyIsoCode(): CurrencyIsoCode = currencyIsoCode

    override fun multiplyAndRound(multiplier: Double): Money {
        val multipliedAmount = amount * multiplier
        val rounded = multipliedAmount.roundToInt()
        return TrustedMoney(rounded, currencyIsoCode)
    }

    override fun equivalent(other: Money): Boolean =
        amount() == other.amount() && currencyIsoCode() == other.currencyIsoCode()

}