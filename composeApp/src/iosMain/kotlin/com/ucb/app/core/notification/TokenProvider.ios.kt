package com.ucb.app.core.notification

actual suspend fun getFcmToken(): String {
    // Para iOS se necesita implementación con APNs
    return ""
}
