package com.ucb.app.analytics.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.app.analytics.domain.usecase.LogEventUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnalyticsWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val logEventUseCase: LogEventUseCase by inject()

    override suspend fun doWork(): Result {
        val eventType = inputData.getString("EVENT_TYPE") ?: "UNKNOWN"
        return try {
            logEventUseCase.invoke(eventType)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            // If we fail for some reason, we can retry
            Result.retry()
        }
    }
}
