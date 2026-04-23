package com.ucb.app.analytics.data.datasource

class AnalyticsRemoteDataSourceImpl(
    private val firebaseManager: AnalyticsFirebaseManager
) : AnalyticsRemoteDataSource {
    override suspend fun saveEvent(eventType: String, timestamp: Long) {
        // Formamos un valor simple para Firebase. 
        // Usamos timestamp como llave para no sobreescribir (ej: "events/123456789")
        val path = "analytics_events/$timestamp"
        // Guardamos un string o JSON stringificado, para simplicidad usamos string simple "eventType"
        firebaseManager.saveEvent(path, eventType)
    }
}
