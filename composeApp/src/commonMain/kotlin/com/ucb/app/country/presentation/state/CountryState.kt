package com.ucb.app.country.presentation.state

import com.ucb.app.country.domain.model.CountryModel

data class CountryState(val isLoading: Boolean = false,
                        val countries: List<CountryModel> = emptyList(),
                        val error: String? = null
)