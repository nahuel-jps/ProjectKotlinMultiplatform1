package com.ucb.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.app.counter.presentation.screen.CounterScreen
import com.ucb.app.counter.presentation.viewmodel.CounterViewModel
import com.ucb.app.detail.presentation.screen.DetailScreen
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.increment.presentation.screen.IncrementScreen
import com.ucb.app.increment.presentation.viewmodel.IncrementViewModel
import com.ucb.app.navigation.AppNavHost
import com.ucb.app.navigation.NavRoute
import com.soft.designsystem.theme.DsTheme

// fun App(startRoute: NavRoute = NavRoute.Config) { // notificacion redireccion
@Composable
@Preview
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }
    DsTheme {
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingVaues ->
            AppNavHost() // AppNavHost(startDest = startRoute)
        }
    }
}