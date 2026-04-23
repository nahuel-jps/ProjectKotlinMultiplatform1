package com.ucb.app.country.data.datasource

import com.ucb.app.country.data.dto.CountryDto

interface CountryRemoteDatasource {
    suspend fun getCountries(): List<CountryDto>
}
