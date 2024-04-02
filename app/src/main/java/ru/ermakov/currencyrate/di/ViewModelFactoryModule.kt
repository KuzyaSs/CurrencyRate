package ru.ermakov.currencyrate.di

import dagger.Module
import dagger.Provides
import ru.ermakov.currencyrate.domain.repository.CurrencyRateRepository
import ru.ermakov.currencyrate.domain.useCase.GetFormattedCurrentDateUseCase
import ru.ermakov.currencyrate.presentation.screen.currencyRates.CurrencyRateViewModelFactory

@Module
class ViewModelFactoryModule {
    @Provides
    fun provideCurrencyRateViewModelFactory(
        currencyRateRepository: CurrencyRateRepository,
        getFormattedCurrentDateUseCase: GetFormattedCurrentDateUseCase
    ): CurrencyRateViewModelFactory {
        return CurrencyRateViewModelFactory(
            currencyRateRepository = currencyRateRepository,
            getFormattedCurrentDateUseCase = getFormattedCurrentDateUseCase
        )
    }
}