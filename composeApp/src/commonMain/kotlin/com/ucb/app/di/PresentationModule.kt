package com.ucb.app.di

import com.ucb.app.dollar.presentation.viewmodel.DollarViewModel
import com.ucb.app.counter.presentation.viewmodel.CounterViewModel
import com.ucb.app.country.presentation.viewmodel.CountryViewModel
import com.ucb.app.crypto.presentation.viewmodel.CryptoViewModel
import com.ucb.app.fakestore.presentation.viewmodel.FakeStoreViewModel
import com.ucb.app.github.presentation.viewmodel.GithubViewModel
import com.ucb.app.increment.presentation.viewmodel.IncrementViewModel
import com.ucb.app.map.presentation.viewmodel.MapViewModel
import com.ucb.app.movie.presentation.viewmodel.MovieViewModel
import com.ucb.app.portfolio.presentation.viewmodel.PortfolioViewModel
import com.ucb.app.nm.login.presentation.viewmodel.LoginViewModel
import com.ucb.app.product_detail.presentation.viewmodel.ProductDetailViewModel
import com.ucb.app.signin.presentation.viewmodel.SigninViewModel
import com.ucb.app.remoteconfig.presentation.viewmodel.RemoteConfigViewModel
import com.ucb.app.analytics.presentation.viewmodel.AnalyticsViewModel
import com.ucb.app.form.presentation.viewmodel.FormViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::CounterViewModel)
    viewModelOf(::IncrementViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::GithubViewModel)
    viewModelOf(::SigninViewModel)
    viewModelOf(::MovieViewModel)
    viewModelOf(::CryptoViewModel)
    viewModelOf(::FakeStoreViewModel)
    viewModelOf(::CountryViewModel)

    viewModelOf(::DollarViewModel)
    viewModelOf(::MapViewModel)
    viewModelOf(::PortfolioViewModel)
    viewModelOf(::RemoteConfigViewModel)
    viewModelOf(::AnalyticsViewModel)
    viewModelOf(::FormViewModel)
}