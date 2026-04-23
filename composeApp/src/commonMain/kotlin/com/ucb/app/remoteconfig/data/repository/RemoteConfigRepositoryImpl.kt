package com.ucb.app.remoteconfig.data.repository

import com.ucb.app.remoteconfig.data.datasource.RemoteConfigManager
import com.ucb.app.remoteconfig.domain.model.AppConfigModel
import com.ucb.app.remoteconfig.domain.repository.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val remoteConfigManager: RemoteConfigManager
) : RemoteConfigRepository {

    override suspend fun getAppConfig(): AppConfigModel {
        // Obligamos a descargar y aplicar los últimos valores desde Firebase
        remoteConfigManager.fetchAndActivate()
        
        // Cosechamos los datos
        val phone = remoteConfigManager.getString("support_phone")
        val url = remoteConfigManager.getString("support_url")

        return AppConfigModel(
            supportPhone = phone,
            supportUrl = url
        )
    }
}
