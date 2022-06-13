package ru.shumskii.timewidget.ui.components.time_view

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TimeViewStateFormatter @Inject constructor() {

    fun format(date: Date): TimeViewState {
        val time = formatDate(HOURS_PATTERN, date)
        val day = formatDate(DAYS_PATTERN, date).uppercase()

        return TimeViewState(
            time = time,
            day = day,
        )
    }

    private fun formatDate(pattern: String, date: Date): String {
        val dateFormatter = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormatter.format(date)
    }

    companion object {
        private const val HOURS_PATTERN = "hh:mm"
        private const val DAYS_PATTERN = "EEE, MMM d"
    }

}