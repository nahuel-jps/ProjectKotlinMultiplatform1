package com.ucb.app.remoteconfig.data.datasource

import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.tasks.await

actual class RemoteConfigManager actual constructor() {
    
    private val remoteConfig: FirebaseRemoteConfig by lazy {
        val instance = FirebaseRemoteConfig.getInstance()
        
        // Configuraciones de desarrollo para permitir descargas más frecuentes (cada 5 segundos)
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(5) 
            .build()
            
        instance.setConfigSettingsAsync(configSettings)

        // Asignar los Default values de fallback local
        val defaults = mapOf(
            "support_phone" to "+591 00000000",
            "support_url" to "https://developer.android.com/support"
        )
        instance.setDefaultsAsync(defaults)
        
        instance
    }

    actual suspend fun fetchAndActivate() {
        try {
            remoteConfig.fetchAndActivate().await()
            println("Firebase Remote Config: Configuraciones actualizadas")
        } catch (e: Exception) {
            println("Firebase Remote Config: Error al actualizar -> ${e.message}")
        }
    }

    actual fun getString(key: String): String {
        return remoteConfig.getString(key)
    }
}
