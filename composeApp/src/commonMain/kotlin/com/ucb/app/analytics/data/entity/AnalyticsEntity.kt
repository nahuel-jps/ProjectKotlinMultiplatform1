package com.ucb.app.analytics.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucb.app.analytics.domain.model.AnalyticsEvent

@Entity(tableName = "analytics_event")
data class AnalyticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val eventType: String,
    val timestamp: Long,
    val isSynced: Boolean = false
) {
    fun toModel() = AnalyticsEvent(
        id = id,
        eventType = eventType,
        timestamp = timestamp,
        isSynced = isSynced
    )
}

fun AnalyticsEvent.toEntity() = AnalyticsEntity(
    id = id,
    eventType = eventType,
    timestamp = timestamp,
    isSynced = isSynced
)
