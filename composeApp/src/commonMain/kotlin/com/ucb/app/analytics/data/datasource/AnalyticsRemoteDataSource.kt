package com.ucb.app.analytics.data.datasource

interface AnalyticsRemoteDataSource {
    suspend fun saveEvent(eventType: String, timestamp: Long)
}
