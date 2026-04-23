package com.ucb.app.portfolio.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

actual class FirebaseManager actual constructor() {
    actual suspend fun saveData(path: String, value: String) {
        // Stub for iOS
    }

    actual suspend fun getCurrency(): Flow<String> = flow {
        // Stub for iOS
        emit("")
    }
}
