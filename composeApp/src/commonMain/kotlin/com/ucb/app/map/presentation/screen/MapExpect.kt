package com.ucb.app.map.presentation.screen

import androidx.compose.runtime.Composable

@Composable
expect fun MapPermissionWrapper(
    onPermissionGranted: @Composable (() -> Unit),
    onPermissionDenied: @Composable (requestPermission: () -> Unit) -> Unit
)

@Composable
expect fun MapContent(latitude: Double, longitude: Double)
