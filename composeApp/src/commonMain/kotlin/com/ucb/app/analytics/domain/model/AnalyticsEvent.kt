package com.ucb.app.analytics.domain.model

data class AnalyticsEvent(
    val id: Int = 0,
    val eventType: String,
    val timestamp: Long,
    val isSynced: Boolean = false
)
