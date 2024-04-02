package ru.ermakov.currencyrate.data.repository

import ru.ermakov.currencyrate.data.remote.dataSource.CurrencyRateRemoteDataSource
import ru.ermakov.currencyrate.domain.model.CurrencyRate
import ru.ermakov.currencyrate.domain.model.Result
import ru.ermakov.currencyrate.domain.repository.CurrencyRateRepository

class CurrencyRateRepositoryImpl(
    private val currencyRateRemoteDataSource: CurrencyRateRemoteDataSource
) : CurrencyRateRepository {
    override suspend fun getCurrencyRates(): Result<List<CurrencyRate>> {
        return currencyRateRemoteDataSource.getCurrencyRates()
    }
}