package com.ucb.app.country.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryNameDto(
    val common: String = ""
)
