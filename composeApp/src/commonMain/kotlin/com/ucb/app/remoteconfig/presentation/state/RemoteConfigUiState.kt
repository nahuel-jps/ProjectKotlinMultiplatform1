package com.ucb.app.remoteconfig.presentation.state

import com.ucb.app.remoteconfig.domain.model.AppConfigModel

data class RemoteConfigUiState(
    val isLoading: Boolean = false,
    val model: AppConfigModel? = null,
    val error: String? = null
)

sealed class RemoteConfigEvent {
    object OnLoadConfig : RemoteConfigEvent()
}
