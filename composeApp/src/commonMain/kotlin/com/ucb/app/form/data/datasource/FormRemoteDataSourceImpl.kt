package com.ucb.app.form.data.datasource

class FormRemoteDataSourceImpl(
    private val firebaseManager: FormFirebaseManager
) : FormRemoteDataSource {
    override suspend fun submitForm(name: String, email: String, message: String, timestamp: Long) {
        val basePath = "form_submissions/$timestamp"
        firebaseManager.saveForm("$basePath/name", name)
        firebaseManager.saveForm("$basePath/email", email)
        firebaseManager.saveForm("$basePath/message", message)
    }
}
