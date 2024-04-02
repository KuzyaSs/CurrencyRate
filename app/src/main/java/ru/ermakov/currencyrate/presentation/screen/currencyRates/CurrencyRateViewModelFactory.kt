package ru.ermakov.currencyrate.presentation.screen.currencyRates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ermakov.currencyrate.domain.repository.CurrencyRateRepository
import ru.ermakov.currencyrate.domain.useCase.GetFormattedCurrentDateUseCase

class CurrencyRateViewModelFactory(
    private val currencyRateRepository: CurrencyRateRepository,
    private val getFormattedCurrentDateUseCase: GetFormattedCurrentDateUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyRateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrencyRateViewModel(
                currencyRateRepository = currencyRateRepository,
                getFormattedCurrentDateUseCase = getFormattedCurrentDateUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}