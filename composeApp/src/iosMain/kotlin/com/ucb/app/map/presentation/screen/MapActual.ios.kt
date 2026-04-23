package com.ucb.app.map.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
actual fun MapPermissionWrapper(
    onPermissionGranted: @Composable (() -> Unit),
    onPermissionDenied: @Composable (requestPermission: () -> Unit) -> Unit
) {
    // iOS: permisos de ubicación se manejan nativamente — se asume concedido en esta implementación stub
    onPermissionGranted()
}

@Composable
actual fun MapContent(latitude: Double, longitude: Double) {
    // iOS: Google Maps para iOS requiere configuración nativa adicional
    Text("Mapa no disponible en iOS (lat: $latitude, lng: $longitude)")
}
