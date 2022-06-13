package ru.shumskii.timewidget.ui.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.shumskii.timewidget.domain.use_cases.UpdateDateUseCase
import javax.inject.Inject

class TimeTickBroadcastReceiverManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val updateDateUseCase: UpdateDateUseCase
) {

    private val broadcastReceiver = make()

    fun registerReceiver() {
        context.registerReceiver(
            broadcastReceiver,
            IntentFilter(Intent.ACTION_TIME_TICK)
        )
    }

    fun unregisterReceiver() {
        try {
            context.unregisterReceiver(broadcastReceiver)
        } catch (e: IllegalArgumentException) {
            Log.e("TimeWidget", "Time tick Receiver not registered", e)
        }
    }

    private fun make(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                if (intent?.action == Intent.ACTION_TIME_TICK) {
                    updateDateUseCase.execute()
                }
            }
        }
    }

}