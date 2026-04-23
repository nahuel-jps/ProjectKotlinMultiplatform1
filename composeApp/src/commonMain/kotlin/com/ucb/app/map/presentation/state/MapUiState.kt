package com.ucb.app.map.presentation.state

import com.ucb.app.map.domain.model.LocationModel

data class MapUiState(
    val isLoading: Boolean = false,
    val location: LocationModel? = null,
    val error: String? = null
)
