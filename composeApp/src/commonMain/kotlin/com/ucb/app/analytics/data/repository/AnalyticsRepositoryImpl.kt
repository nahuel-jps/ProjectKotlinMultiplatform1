package com.ucb.app.analytics.data.repository

import com.ucb.app.analytics.data.datasource.AnalyticsLocalDataSource
import com.ucb.app.analytics.data.datasource.AnalyticsRemoteDataSource
import com.ucb.app.analytics.data.entity.AnalyticsEntity
import com.ucb.app.analytics.domain.repository.AnalyticsRepository
import com.ucb.app.analytics.utils.getCurrentTimeMillis

class AnalyticsRepositoryImpl(
    private val localDataSource: AnalyticsLocalDataSource,
    private val remoteDataSource: AnalyticsRemoteDataSource
) : AnalyticsRepository {
    override suspend fun saveEvent(eventType: String) {
        val timestamp = getCurrentTimeMillis()
        val entity = AnalyticsEntity(
            eventType = eventType,
            timestamp = timestamp,
            isSynced = false
        )
        localDataSource.save(entity)
        
        // Triggers the synchronization
        syncUnsyncedEvents()
    }

    override suspend fun syncUnsyncedEvents() {
        val unsyncedEvents = localDataSource.getUnsyncedEvents()
        for (event in unsyncedEvents) {
            try {
                remoteDataSource.saveEvent(event.eventType, event.timestamp)
                // If remote save succeeds, update local entity to synced
                val updatedEvent = event.copy(isSynced = true)
                localDataSource.update(updatedEvent)
            } catch (e: Exception) {
                // If it fails, we keep it as unsynced
                println("Failed to sync event ${event.id}: ${e.message}")
            }
        }
    }
}
