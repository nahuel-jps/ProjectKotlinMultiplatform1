package com.ucb.app.analytics.domain.repository

import com.ucb.app.analytics.domain.model.AnalyticsEvent

interface AnalyticsRepository {
    suspend fun saveEvent(eventType: String)
    suspend fun syncUnsyncedEvents()
}
