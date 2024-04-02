package ru.ermakov.currencyrate.data.remote.dataSource

import ru.ermakov.currencyrate.data.remote.api.CurrencyRateApi
import ru.ermakov.currencyrate.data.remote.model.toCurrencyRate
import ru.ermakov.currencyrate.domain.model.CurrencyRate
import ru.ermakov.currencyrate.domain.model.Result
import javax.inject.Inject

private const val CONNECTION_FAILURE_EXCEPTION = "Connection failure"

class CurrencyRateRemoteDataSourceImpl @Inject constructor(
    private val currencyRateApi: CurrencyRateApi
) : CurrencyRateRemoteDataSource {
    override suspend fun getCurrencyRates(): Result<List<CurrencyRate>> {
        try {
            val response = currencyRateApi.getCurrencyRates()
            if (response.isSuccessful) {
                response.body()?.let { remoteCurrencyRateResponse ->
                    return Result.Success(
                        data = remoteCurrencyRateResponse
                            .remoteCurrencyRates
                            .values
                            .map { remoteCurrencyRate ->
                                remoteCurrencyRate.toCurrencyRate()
                            }
                    )
                }
            }
            return Result.Error(errorMessage = CONNECTION_FAILURE_EXCEPTION)
        } catch (exception: Exception) {
            return Result.Error(errorMessage = exception.message.toString())
        }
    }
}