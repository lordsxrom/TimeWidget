package ru.shumskii.timewidget.ui.widgets.time_widget

import android.content.Context
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import ru.shumskii.timewidget.domain.use_cases.GetDateFlowUseCase
import ru.shumskii.timewidget.ui.receivers.TimeTickBroadcastReceiverManager
import javax.inject.Inject

@AndroidEntryPoint
class TimeWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget = TimeWidget()

    @Inject
    lateinit var timeTickBroadcastReceiverManager: TimeTickBroadcastReceiverManager

    @Inject
    lateinit var getDateFlowUseCase: GetDateFlowUseCase

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        observeData(context)
        timeTickBroadcastReceiverManager.registerReceiver()
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        timeTickBroadcastReceiverManager.unregisterReceiver()
        coroutineScope.cancel()
    }

    private fun observeData(context: Context) {
        coroutineScope.launch {
            GlanceAppWidgetManager(context)
                .getGlanceIds(TimeWidget::class.java)
                .firstOrNull()?.let { glanceId ->
                    getDateFlowUseCase.execute().collect { date ->
                        updateAppWidgetState(
                            context,
                            PreferencesGlanceStateDefinition,
                            glanceId
                        ) { preferences ->
                            preferences.toMutablePreferences().apply {
                                this[timeKey] = date.time
                            }
                        }
                        glanceAppWidget.update(context, glanceId)
                    }
                }
        }
    }

    companion object {
        val timeKey = longPreferencesKey("time")
    }

}