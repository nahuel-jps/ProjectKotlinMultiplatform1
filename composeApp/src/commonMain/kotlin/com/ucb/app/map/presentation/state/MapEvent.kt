package com.ucb.app.map.presentation.state

sealed interface MapEvent {
    data object OnStartLocation : MapEvent
}
