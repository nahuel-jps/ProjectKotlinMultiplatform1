package com.ucb.app.remoteconfig.data.datasource

expect class RemoteConfigManager() {
    suspend fun fetchAndActivate()
    fun getString(key: String): String
}
