package ru.shumskii.timewidget.ui.widgets.time_widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.currentState
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.PreferencesGlanceStateDefinition
import ru.shumskii.timewidget.ui.components.time_view.TimeView
import ru.shumskii.timewidget.ui.components.time_view.TimeViewStateFormatter
import java.util.*
import javax.inject.Inject

class TimeWidget @Inject constructor() : GlanceAppWidget() {

    override var stateDefinition = PreferencesGlanceStateDefinition

    @Inject
    lateinit var timeViewStateFormatter: TimeViewStateFormatter

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val time = prefs[TimeWidgetReceiver.timeKey] ?: Date().time

        val date = Date(time)
        val state = timeViewStateFormatter.format(date)

        TimeView(
            state = state,
            modifier = GlanceModifier
                .fillMaxSize()
                .appWidgetBackground()
                .cornerRadius(16.dp)
                .padding(8.dp),
        )
    }

}