package com.ucb.app.country.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val name: CountryNameDto = CountryNameDto(),
    val flags: CountryFlagsDto = CountryFlagsDto(),
    val capital: List<String> = emptyList(),
    val region: String = "",
    val population: Long = 0
)
