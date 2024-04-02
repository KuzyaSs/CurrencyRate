package ru.ermakov.currencyrate.domain.repository

import ru.ermakov.currencyrate.domain.model.CurrencyRate
import ru.ermakov.currencyrate.domain.model.Result

interface CurrencyRateRepository {
    suspend fun getCurrencyRates(): Result<List<CurrencyRate>>
}