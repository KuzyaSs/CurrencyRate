package ru.ermakov.currencyrate

import android.app.Application
import ru.ermakov.currencyrate.di.ApplicationComponent
import ru.ermakov.currencyrate.di.DaggerApplicationComponent

class CurrencyRateApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}