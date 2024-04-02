package ru.ermakov.currencyrate.di

import dagger.Component
import ru.ermakov.currencyrate.presentation.screen.currencyRates.CurrencyRateActivity

@Component(modules = [RemoteModule::class, RepositoryModule::class, ViewModelFactoryModule::class])
interface ApplicationComponent {
    fun inject(activity: CurrencyRateActivity)
}