package com.ucb.app.analytics.domain.usecase

import com.ucb.app.analytics.domain.repository.AnalyticsRepository

class LogEventUseCase(
    private val repository: AnalyticsRepository
) {
    suspend fun invoke(eventType: String) {
        try {
            repository.saveEvent(eventType)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
