package com.ucb.app.country.domain.repository

import com.ucb.app.country.domain.model.CountryModel

interface CountryRepository {
    suspend fun getCountries(): List<CountryModel>
}
