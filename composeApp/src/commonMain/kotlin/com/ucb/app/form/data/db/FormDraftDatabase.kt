package com.ucb.app.form.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ucb.app.form.data.dao.FormDraftDao
import com.ucb.app.form.data.entity.FormDraftEntity

@Database(entities = [FormDraftEntity::class], version = 1)
@ConstructedBy(FormDraftDatabaseConstructor::class)
abstract class FormDraftDatabase : RoomDatabase() {
    abstract fun getDao(): FormDraftDao
}

@Suppress("KotlinNoActualForExpect")
expect object FormDraftDatabaseConstructor : RoomDatabaseConstructor<FormDraftDatabase> {
    override fun initialize(): FormDraftDatabase
}

expect fun getFormDraftDatabaseBuilder(ctx: Any?): RoomDatabase.Builder<FormDraftDatabase>
