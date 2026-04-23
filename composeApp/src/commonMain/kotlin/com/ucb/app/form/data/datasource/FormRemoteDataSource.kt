package com.ucb.app.form.data.datasource

interface FormRemoteDataSource {
    suspend fun submitForm(name: String, email: String, message: String, timestamp: Long)
}
