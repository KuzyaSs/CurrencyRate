package ru.ermakov.currencyrate.data.remote.dataSource

import ru.ermakov.currencyrate.domain.model.CurrencyRate
import ru.ermakov.currencyrate.domain.model.Result

interface CurrencyRateRemoteDataSource {
    suspend fun getCurrencyRates(): Result<List<CurrencyRate>>
}