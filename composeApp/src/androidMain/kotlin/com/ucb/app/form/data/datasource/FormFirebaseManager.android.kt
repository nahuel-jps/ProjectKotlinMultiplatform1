package com.ucb.app.form.data.datasource

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

actual class FormFirebaseManager actual constructor() {
    private val database = FirebaseDatabase.getInstance().reference

    actual suspend fun saveForm(path: String, value: String) {
        try {
            database.child(path).setValue(value).await()
            println("Firebase Form: Guardado exitoso en $path")
        } catch (e: Exception) {
            println("Firebase Form: Error - ${e.message}")
        }
    }
}
