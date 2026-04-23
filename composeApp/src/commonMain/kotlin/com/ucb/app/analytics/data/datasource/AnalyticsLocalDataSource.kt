package com.ucb.app.analytics.data.datasource

import com.ucb.app.analytics.data.entity.AnalyticsEntity

interface AnalyticsLocalDataSource {
    suspend fun save(entity: AnalyticsEntity)
    suspend fun getUnsyncedEvents(): List<AnalyticsEntity>
    suspend fun update(entity: AnalyticsEntity)
}
