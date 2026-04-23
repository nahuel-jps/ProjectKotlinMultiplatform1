package com.ucb.app.form.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.ucb.app.form.domain.model.FormDraft

class FormSyncScheduler(private val context: Context) {

    fun scheduleSubmit(draft: FormDraft) {
        val data = Data.Builder()
            .putString("name", draft.name)
            .putString("email", draft.email)
            .putString("message", draft.message)
            .putLong("timestamp", draft.lastSavedAt)
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(FormSubmitWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
