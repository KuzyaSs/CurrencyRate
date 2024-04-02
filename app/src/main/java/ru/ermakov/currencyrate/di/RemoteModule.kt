package ru.ermakov.currencyrate.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ermakov.currencyrate.data.remote.api.CurrencyRateApi
import ru.ermakov.currencyrate.data.remote.dataSource.CurrencyRateRemoteDataSource
import ru.ermakov.currencyrate.data.remote.dataSource.CurrencyRateRemoteDataSourceImpl

private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

@Module
class RemoteModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCurrencyRateApi(retrofit: Retrofit): CurrencyRateApi {
        return retrofit.create(CurrencyRateApi::class.java)
    }

    @Provides
    fun provideCurrencyRateRemoteDataSource(currencyRateApi: CurrencyRateApi): CurrencyRateRemoteDataSource {
        return CurrencyRateRemoteDataSourceImpl(currencyRateApi = currencyRateApi)
    }
}