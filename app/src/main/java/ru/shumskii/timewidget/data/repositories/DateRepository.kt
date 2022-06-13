package ru.shumskii.timewidget.data.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateRepository @Inject constructor() {

    private val _date = MutableStateFlow(Date())
    val date: StateFlow<Date> = _date.asStateFlow()

    fun setDate(date: Date) {
        _date.value = date
    }

}