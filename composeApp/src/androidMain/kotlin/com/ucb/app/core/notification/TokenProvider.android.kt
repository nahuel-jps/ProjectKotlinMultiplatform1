package com.ucb.app.core.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual suspend fun getFcmToken(): String = suspendCoroutine { continuation ->
    FirebaseMessaging.getInstance().token
        .addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FIREBASE_FCM", "Fetching FCM registration token failed", task.exception)
                continuation.resumeWithException(task.exception ?: Exception("Unknown error getting FCM token"))
                return@addOnCompleteListener
            }

            // Si la tarea fue exitosa, obtenemos el token de notificaciones
            val token = task.result
            Log.d("FIREBASE_FCM", "FCM Token obtenido exitosamente: $token")
            continuation.resume(token ?: "")
        }
}
