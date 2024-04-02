package ru.ermakov.currencyrate.domain.model

sealed interface Result<T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val errorMessage: String? = null) : Result<T>
}