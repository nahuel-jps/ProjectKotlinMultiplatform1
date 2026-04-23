package com.ucb.app.map.presentation.state

sealed interface MapEffect {
    data class ShowError(val message: String) : MapEffect
}
