package com.ucb.app.analytics.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ucb.app.analytics.data.dao.AnalyticsDao
import com.ucb.app.analytics.data.entity.AnalyticsEntity

@Database(entities = [AnalyticsEntity::class], version = 1)
@ConstructedBy(AnalyticsDatabaseConstructor::class)
abstract class AnalyticsDatabase : RoomDatabase() {
    abstract fun getDao(): AnalyticsDao
}

@Suppress("KotlinNoActualForExpect")
expect object AnalyticsDatabaseConstructor : RoomDatabaseConstructor<AnalyticsDatabase> {
    override fun initialize(): AnalyticsDatabase
}

expect fun getAnalyticsDatabaseBuilder(ctx: Any?): RoomDatabase.Builder<AnalyticsDatabase>
