package com.ucb.app.remoteconfig.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.remoteconfig.domain.usecase.GetRemoteConfigUseCase
import com.ucb.app.remoteconfig.presentation.state.RemoteConfigEvent
import com.ucb.app.remoteconfig.presentation.state.RemoteConfigUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemoteConfigViewModel(
    private val getRemoteConfigUseCase: GetRemoteConfigUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RemoteConfigUiState())
    val state: StateFlow<RemoteConfigUiState> = _state.asStateFlow()

    fun onEvent(event: RemoteConfigEvent) {
        when (event) {
            is RemoteConfigEvent.OnLoadConfig -> {
                fetchConfig()
            }
        }
    }

    private fun fetchConfig() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                // Solicitar toda la configuración de red y base de datos local
                val config = getRemoteConfigUseCase()
                _state.value = _state.value.copy(isLoading = false, model = config)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "No se pudieron cargar los datos."
                )
            }
        }
    }
}
