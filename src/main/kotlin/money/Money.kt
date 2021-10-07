package money

typealias CurrencyIsoCode = String

interface Money {
    fun amount(): Int
    fun currencyIsoCode(): CurrencyIsoCode
    fun multiplyAndRound(multiplier: Double)
    fun equivalent(other: Money): Boolean
}