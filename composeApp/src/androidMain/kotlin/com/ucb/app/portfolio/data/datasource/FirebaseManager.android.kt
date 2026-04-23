package com.ucb.app.portfolio.data.datasource

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.tasks.await


actual class FirebaseManager actual constructor(){
    private val database = FirebaseDatabase.getInstance().reference

    actual suspend fun saveData(path: String, value: String) {
        try {
            database.child(path).setValue(value).await()
            println("Firebase Android: Guardado exitoso en $path")
        } catch (e: Exception) {
            println("Firebase Android: Error - ${e.message}")
        }
    }

    actual suspend fun getCurrency(): kotlinx.coroutines.flow.Flow<String> = kotlinx.coroutines.flow.callbackFlow {
        val listener = object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val value = snapshot.getValue(String::class.java) ?: ""
                trySend(value)
            }
            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                close(error.toException())
            }
        }
        database.child("currency").addValueEventListener(listener)
        awaitClose {
            database.child("currency").removeEventListener(listener)
        }
    }
}
