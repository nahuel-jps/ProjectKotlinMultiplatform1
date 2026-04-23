package com.ucb.app.map.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucb.app.map.presentation.state.MapEvent
import com.ucb.app.map.presentation.viewmodel.MapViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MapScreen(viewModel: MapViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    MapPermissionWrapper(
        onPermissionGranted = {
            LaunchedEffect(Unit) {
                viewModel.onEvent(MapEvent.OnStartLocation)
            }

            when {
                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                state.location != null -> {
                    val loc = state.location!!
                    MapContent(
                        latitude = loc.latitude,
                        longitude = loc.longitude
                    )
                }
                state.error != null -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${state.error}")
                    }
                }
                else -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        },
        onPermissionDenied = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Se necesita permiso de ubicación para mostrar el mapa.")
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { it() }) {
                        Text("Solicitar permiso")
                    }
                }
            }
        }
    )
}
