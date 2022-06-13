package ru.shumskii.timewidget.ui.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import ru.shumskii.timewidget.domain.use_cases.UpdateDateUseCase
import javax.inject.Inject

@AndroidEntryPoint
class TimeChangeBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var updateDateUseCase: UpdateDateUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action == Intent.ACTION_TIME_CHANGED || action == Intent.ACTION_TIMEZONE_CHANGED) {
            updateDateUseCase.execute()
        }
    }

}