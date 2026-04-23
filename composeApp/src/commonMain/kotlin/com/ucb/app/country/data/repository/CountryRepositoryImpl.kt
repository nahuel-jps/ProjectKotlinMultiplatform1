package com.ucb.app.country.data.repository

import com.ucb.app.country.data.datasource.CountryRemoteDatasource
import com.ucb.app.country.data.mapper.toModel
import com.ucb.app.country.domain.repository.CountryRepository
import com.ucb.app.country.domain.model.CountryModel

class CountryRepositoryImpl(
    val remote: CountryRemoteDatasource
) : CountryRepository {
    override suspend fun getCountries(): List<CountryModel> {
        val response = remote.getCountries()
        return response.map { it.toModel() }
    }
}
