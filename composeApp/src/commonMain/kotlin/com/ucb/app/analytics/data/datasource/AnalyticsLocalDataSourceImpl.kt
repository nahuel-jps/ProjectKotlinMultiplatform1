package com.ucb.app.analytics.data.datasource

import com.ucb.app.analytics.data.dao.AnalyticsDao
import com.ucb.app.analytics.data.entity.AnalyticsEntity

class AnalyticsLocalDataSourceImpl(
    private val dao: AnalyticsDao
) : AnalyticsLocalDataSource {
    override suspend fun save(entity: AnalyticsEntity) {
        dao.insert(entity)
    }

    override suspend fun getUnsyncedEvents(): List<AnalyticsEntity> {
        return dao.getUnsyncedEvents()
    }

    override suspend fun update(entity: AnalyticsEntity) {
        dao.update(entity)
    }
}
