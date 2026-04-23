package com.ucb.app.remoteconfig.domain.repository

import com.ucb.app.remoteconfig.domain.model.AppConfigModel

interface RemoteConfigRepository {
    suspend fun getAppConfig(): AppConfigModel
}
