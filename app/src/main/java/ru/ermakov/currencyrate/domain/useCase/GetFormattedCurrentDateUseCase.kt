package ru.ermakov.currencyrate.domain.useCase

import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class GetFormattedCurrentDateUseCase @Inject constructor(){
    operator fun invoke(): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm:ss, d MMMM")
        return formatter.format(date)
    }
}