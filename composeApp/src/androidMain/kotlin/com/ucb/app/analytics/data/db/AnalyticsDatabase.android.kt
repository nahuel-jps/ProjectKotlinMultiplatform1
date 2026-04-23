package com.ucb.app.analytics.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual fun getAnalyticsDatabaseBuilder(ctx: Any?): RoomDatabase.Builder<AnalyticsDatabase> {
    val appContext = ctx as Context
    val dbFile = appContext.getDatabasePath("analytics.db")
    return Room.databaseBuilder<AnalyticsDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
