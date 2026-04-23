package com.ucb.app.analytics.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ucb.app.analytics.data.entity.AnalyticsEntity

@Dao
interface AnalyticsDao {
    @Insert
    suspend fun insert(event: AnalyticsEntity)

    @Query("SELECT * FROM analytics_event WHERE isSynced = 0")
    suspend fun getUnsyncedEvents(): List<AnalyticsEntity>

    @Update
    suspend fun update(event: AnalyticsEntity)
}
