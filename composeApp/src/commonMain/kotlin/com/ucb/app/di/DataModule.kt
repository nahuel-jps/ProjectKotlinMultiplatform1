package com.ucb.app.di

import com.ucb.app.country.data.datasource.CountryRemoteDatasource
import com.ucb.app.country.data.repository.CountryRepositoryImpl
import com.ucb.app.country.data.service.CountryService
import com.ucb.app.country.domain.repository.CountryRepository
import com.ucb.app.dollar.data.datasource.DollarLocalDataSource
import com.ucb.app.dollar.data.repository.DollarRepositoryImpl
import com.ucb.app.dollar.data.service.DbService
import com.ucb.app.dollar.domain.repository.DollarRepository
import com.ucb.app.github.data.datasource.GithubRemoteDataSource
import com.ucb.app.github.data.repository.GithubRepositoryImpl
import com.ucb.app.github.data.service.GitHubApiService
import com.ucb.app.github.domain.repository.GithubRepository
import com.ucb.app.movie.data.datasource.MovieRemoteDatasource
import com.ucb.app.movie.data.repository.MovieRepositoryImpl
import com.ucb.app.movie.data.service.MovieService
import com.ucb.app.movie.domain.repository.MovieRepository
import com.ucb.app.portfolio.data.datasource.FirebaseManager
import com.ucb.app.portfolio.data.repository.PortfolioRepositoryImpl
import com.ucb.app.portfolio.domain.repository.PortfolioRepository
import com.ucb.app.remoteconfig.data.datasource.RemoteConfigManager
import com.ucb.app.remoteconfig.data.repository.RemoteConfigRepositoryImpl
import com.ucb.app.remoteconfig.domain.repository.RemoteConfigRepository
import com.ucb.app.analytics.data.datasource.AnalyticsLocalDataSource
import com.ucb.app.analytics.data.datasource.AnalyticsLocalDataSourceImpl
import com.ucb.app.analytics.data.datasource.AnalyticsRemoteDataSource
import com.ucb.app.analytics.data.datasource.AnalyticsRemoteDataSourceImpl
import com.ucb.app.analytics.data.datasource.AnalyticsFirebaseManager
import com.ucb.app.analytics.data.repository.AnalyticsRepositoryImpl
import com.ucb.app.analytics.domain.repository.AnalyticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::GitHubApiService).bind<GithubRemoteDataSource>()
    singleOf(::GithubRepositoryImpl).bind<GithubRepository>()
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::MovieService).bind<MovieRemoteDatasource>()
    singleOf(::CountryService).bind<CountryRemoteDatasource>()
    singleOf(::CountryRepositoryImpl).bind<CountryRepository>()

    singleOf(::DollarRepositoryImpl).bind<DollarRepository>()
    singleOf(::DbService).bind<DollarLocalDataSource>()

    singleOf(::FirebaseManager)
    singleOf(::PortfolioRepositoryImpl).bind<PortfolioRepository>()

    singleOf(::RemoteConfigManager)
    singleOf(::RemoteConfigRepositoryImpl).bind<RemoteConfigRepository>()

    singleOf(::AnalyticsFirebaseManager)
    singleOf(::AnalyticsRemoteDataSourceImpl).bind<AnalyticsRemoteDataSource>()
    singleOf(::AnalyticsLocalDataSourceImpl).bind<AnalyticsLocalDataSource>()
    singleOf(::AnalyticsRepositoryImpl).bind<AnalyticsRepository>()
}