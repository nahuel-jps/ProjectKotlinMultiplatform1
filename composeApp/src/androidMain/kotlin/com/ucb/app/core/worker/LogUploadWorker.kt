package com.ucb.app.core.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ucb.app.movie.domain.usecase.GetMoviesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogUploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    // Extraemos la lógica de negocios limpiecita usando Koin Component
    private val getMoviesUseCase: GetMoviesUseCase by inject()

    override suspend fun doWork(): Result {
        print("ejecutar instrucción para subir datos")

        Log.d("WORK_MANAGER", "LogUploadWorker ha despertado. Intentando simular tarea en segundo plano...")
        try {
            // Simulamos obtener datos ejecutando el UseCase de dominio
            val movies = getMoviesUseCase.invoke()
            Log.d("WORK_MANAGER", "[Éxito] Se simula la subida. Datos recolectados en background: ${movies.size} películas.")
            // En un entorno real aquí mandaríamos esos datos a un backend analítico (Firebase Analytics, Crashlytics, etc.)
            
            return Result.success()
        } catch (e: Exception) {
            Log.e("WORK_MANAGER", "[Fallo] Hubo un error al ejecutar el trabajo: ${e.message}")
            return Result.failure()
        }
    }
}
