package com.ucb.app.di

import com.ucb.app.dollar.domain.usecase.CreateDollarUseCase
import com.ucb.app.dollar.domain.usecase.GetDollarListUsecase
import com.ucb.app.country.domain.usecase.GetCountriesUseCase
import com.ucb.app.github.domain.usecase.GetAvatarUseCase
import com.ucb.app.map.domain.usecase.GetLocationUseCase
import com.ucb.app.movie.domain.usecase.GetMoviesUseCase
import com.ucb.app.portfolio.domain.usecase.GetPortfolioUseCase
import com.ucb.app.portfolio.domain.usecase.SavePortfolioUseCase
import com.ucb.app.remoteconfig.domain.usecase.GetRemoteConfigUseCase
import com.ucb.app.analytics.domain.usecase.LogEventUseCase
import com.ucb.app.form.domain.usecase.LoadDraftUseCase
import com.ucb.app.form.domain.usecase.SaveDraftUseCase
import com.ucb.app.form.domain.usecase.SubmitFormUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAvatarUseCase)
    singleOf(::GetMoviesUseCase)
    singleOf(::GetCountriesUseCase)
    singleOf(::GetLocationUseCase)

    singleOf(::GetDollarListUsecase)
    singleOf(::CreateDollarUseCase)

    singleOf(::GetPortfolioUseCase)
    singleOf(::SavePortfolioUseCase)
    
    singleOf(::GetRemoteConfigUseCase)
    singleOf(::LogEventUseCase)

    singleOf(::SaveDraftUseCase)
    singleOf(::LoadDraftUseCase)
    singleOf(::SubmitFormUseCase)
}