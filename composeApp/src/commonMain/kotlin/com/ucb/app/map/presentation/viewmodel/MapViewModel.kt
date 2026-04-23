package com.ucb.app.map.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.map.domain.usecase.GetLocationUseCase
import com.ucb.app.map.presentation.state.MapEffect
import com.ucb.app.map.presentation.state.MapEvent
import com.ucb.app.map.presentation.state.MapUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel(
    val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MapUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<MapEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.OnStartLocation -> startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getLocationUseCase.invoke()
                .catch { e ->
                    _state.update { it.copy(isLoading = false, error = e.message) }
                    _effect.send(MapEffect.ShowError(e.message ?: "Error de ubicación"))
                }
                .collect { location ->
                    _state.update {
                        it.copy(isLoading = false, location = location)
                    }
                }
        }
    }
}
