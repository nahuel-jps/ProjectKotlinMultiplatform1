package com.ucb.app.remoteconfig.data.datasource

actual class RemoteConfigManager actual constructor() {
    actual suspend fun fetchAndActivate() {
        // Stub implementation, iOS not fully implemented yet
    }

    actual fun getString(key: String): String {
        return ""
    }
}
