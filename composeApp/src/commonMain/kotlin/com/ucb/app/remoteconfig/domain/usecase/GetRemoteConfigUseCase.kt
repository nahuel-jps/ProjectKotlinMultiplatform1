package com.ucb.app.remoteconfig.domain.usecase

import com.ucb.app.remoteconfig.domain.model.AppConfigModel
import com.ucb.app.remoteconfig.domain.repository.RemoteConfigRepository

class GetRemoteConfigUseCase(private val repository: RemoteConfigRepository) {
    suspend operator fun invoke(): AppConfigModel {
        return repository.getAppConfig()
    }
}
