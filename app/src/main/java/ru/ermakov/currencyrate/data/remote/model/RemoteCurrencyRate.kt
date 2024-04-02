package ru.ermakov.currencyrate.data.remote.model

import com.google.gson.annotations.SerializedName
import ru.ermakov.currencyrate.domain.model.CurrencyRate

data class RemoteCurrencyRate(
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val rate: Double,
    @SerializedName("Previous")
    val previousRate: Double
)

fun RemoteCurrencyRate.toCurrencyRate(): CurrencyRate {
    return CurrencyRate(
        charCode = charCode,
        name = name,
        rate = rate,
        previousRate = previousRate
    )
}