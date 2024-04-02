package ru.ermakov.currencyrate.presentation.screen.currencyRates

import ru.ermakov.currencyrate.domain.model.CurrencyRate

data class CurrencyRatesUiState(
    val currencyRates: List<CurrencyRate> = emptyList(),
    val lastUpdateDate: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
