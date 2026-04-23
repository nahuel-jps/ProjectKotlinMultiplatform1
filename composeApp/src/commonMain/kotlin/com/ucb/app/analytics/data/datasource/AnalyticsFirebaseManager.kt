package com.ucb.app.analytics.data.datasource

expect class AnalyticsFirebaseManager() {
    suspend fun saveEvent(path: String, value: String)
}
