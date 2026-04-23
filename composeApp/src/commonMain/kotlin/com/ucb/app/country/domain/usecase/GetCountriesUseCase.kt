package com.ucb.app.country.domain.usecase

import com.ucb.app.country.domain.repository.CountryRepository
import com.ucb.app.country.domain.model.CountryModel

class GetCountriesUseCase(
    val repository: CountryRepository
) {
    suspend fun invoke(): List<CountryModel> {
        return repository.getCountries()
    }
}
