package com.ucb.app.analytics.data.datasource

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

actual class AnalyticsFirebaseManager actual constructor() {
    private val database = FirebaseDatabase.getInstance().reference

    actual suspend fun saveEvent(path: String, value: String) {
        try {
            database.child(path).setValue(value).await()
            println("Firebase Android Analytics: Guardado exitoso en $path")
        } catch (e: Exception) {
            println("Firebase Android Analytics: Error - ${e.message}")
        }
    }
}
