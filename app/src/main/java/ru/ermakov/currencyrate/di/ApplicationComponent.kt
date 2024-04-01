package ru.ermakov.currencyrate.di

import dagger.Component

@Component(modules = [RemoteModule::class])
interface ApplicationComponent {
}