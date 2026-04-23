package com.ucb.app.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ucb.app.core.data.db.AppDatabase
import com.ucb.app.analytics.data.db.AnalyticsDatabase
import com.ucb.app.analytics.data.db.getAnalyticsDatabaseBuilder
import com.ucb.app.map.data.repository.LocationRepositoryImpl
import com.ucb.app.map.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        val context = androidContext()
        val dbFile = context.getDatabasePath("dollar_db.db")
        Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { get<AppDatabase>().getDao() }

    single<AnalyticsDatabase> {
        val context = androidContext()
        getAnalyticsDatabaseBuilder(context)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { get<AnalyticsDatabase>().getDao() }

    single<LocationRepository> {
        LocationRepositoryImpl(androidContext())
    }
}