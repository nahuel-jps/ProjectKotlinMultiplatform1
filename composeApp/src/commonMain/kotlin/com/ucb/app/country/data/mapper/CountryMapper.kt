package com.ucb.app.country.data.mapper

import com.ucb.app.country.data.dto.CountryDto
import com.ucb.app.country.domain.model.CountryModel

fun CountryDto.toModel() = CountryModel(
    name = name.common,
    capital = capital.firstOrNull() ?: "",
    region = region,
    population = population,
    flag = flags.png
)
