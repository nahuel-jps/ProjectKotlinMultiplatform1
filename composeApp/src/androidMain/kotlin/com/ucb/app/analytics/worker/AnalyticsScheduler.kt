package com.ucb.app.analytics.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class AnalyticsScheduler(private val context: Context) {
    fun scheduleLogEvent(eventType: String) {
        val data = Data.Builder().putString("EVENT_TYPE", eventType).build()
        
        // Exigimos red conectada para intentar sincronizar inmediatamente a Firebase
        // Si no hay red, WorkManager encolará y ejecutará cuando haya.
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
            
        val workRequest = OneTimeWorkRequest.Builder(AnalyticsWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
            
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
