package com.ucb.app.form.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.usecase.SubmitFormUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FormSubmitWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val submitFormUseCase: SubmitFormUseCase by inject()

    override suspend fun doWork(): Result {
        val name = inputData.getString("name") ?: ""
        val email = inputData.getString("email") ?: ""
        val message = inputData.getString("message") ?: ""
        val timestamp = inputData.getLong("timestamp", System.currentTimeMillis())

        return try {
            val draft = FormDraft(
                name = name,
                email = email,
                message = message,
                lastSavedAt = timestamp,
                isSubmitted = true
            )
            submitFormUseCase.invoke(draft)
            Log.d("FORM_WORKER", "Formulario enviado a Firebase correctamente")
            Result.success()
        } catch (e: Exception) {
            Log.e("FORM_WORKER", "Error al enviar formulario: ${e.message}")
            Result.retry()
        }
    }
}
