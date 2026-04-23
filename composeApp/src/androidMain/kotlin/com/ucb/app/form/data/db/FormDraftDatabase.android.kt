package com.ucb.app.form.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual fun getFormDraftDatabaseBuilder(ctx: Any?): RoomDatabase.Builder<FormDraftDatabase> {
    val appContext = ctx as Context
    val dbFile = appContext.getDatabasePath("form_draft.db")
    return Room.databaseBuilder<FormDraftDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
