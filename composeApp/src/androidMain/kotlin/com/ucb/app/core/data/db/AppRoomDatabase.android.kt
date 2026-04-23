package com.ucb.app.core.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ucb.app.core.data.db.AppDatabase

actual fun getDatabaseBuilder(ctx: Any?): RoomDatabase.Builder<AppDatabase> {
    val appContext = (ctx as Context).applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}