package com.ucb.app.form.data.datasource

expect class FormFirebaseManager() {
    suspend fun saveForm(path: String, value: String)
}
