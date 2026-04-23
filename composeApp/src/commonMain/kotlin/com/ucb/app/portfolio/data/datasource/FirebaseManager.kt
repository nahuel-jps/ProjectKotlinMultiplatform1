package com.ucb.app.portfolio.data.datasource

import kotlinx.coroutines.flow.Flow

expect class FirebaseManager() {
    suspend fun saveData(path: String, value: String)
    suspend fun getCurrency(): Flow<String>
}
