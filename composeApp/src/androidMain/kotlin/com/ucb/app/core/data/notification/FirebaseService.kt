package com.ucb.app.core.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ucb.app.MainActivity

class FirebaseService : FirebaseMessagingService() {

    companion object {
        val TAG = FirebaseService::class.java.simpleName
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        // Aquí podrías enviar este token a tu propio backend para saber a qué dispositivo apuntar
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            // Aquí puedes procesar datos ocultos recibidos
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            // FORZAR MOSTRAR NOTIFICACIÓN EN PRIMER PLANO
            showNotification(it.title, it.body, remoteMessage.data)
        }
    }

    private fun showNotification(title: String?, message: String?, data: Map<String, String>) {
        val channelId = "default_fcm_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Crear canal de notificación (Requerido API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notificaciones Principales", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        // Preparar el Intent para cuando hagan clic
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // Pasamos los extras customizados que mandaste (ej. target_screen = github)
            data.forEach { (key, value) -> putExtra(key, value) }
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construir la Notificación visual
        val builder = NotificationCompat.Builder(this, channelId)
            // Asegúrate de que el icono blanco transparente exista, usamos genérico por si acaso
            .setSmallIcon(android.R.drawable.ic_dialog_info) 
            .setContentTitle(title ?: "Nueva Notificación")
            .setContentText(message ?: "")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
