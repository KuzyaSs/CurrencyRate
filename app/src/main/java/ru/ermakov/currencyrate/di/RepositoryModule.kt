package ru.ermakov.currencyrate.di

import dagger.Module
import dagger.Provides
import ru.ermakov.currencyrate.data.remote.dataSource.CurrencyRateRemoteDataSource
import ru.ermakov.currencyrate.data.repository.CurrencyRateRepositoryImpl
import ru.ermakov.currencyrate.domain.repository.CurrencyRateRepository

@Module
class RepositoryModule {
    @Provides
    fun provideCurrencyRateRepository(
        currencyRateRemoteDataSource: CurrencyRateRemoteDataSource
    ): CurrencyRateRepository {
        return CurrencyRateRepositoryImpl(currencyRateRemoteDataSource = currencyRateRemoteDataSource)
    }
}