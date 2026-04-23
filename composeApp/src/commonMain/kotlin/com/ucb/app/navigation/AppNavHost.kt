package com.ucb.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.app.country.presentation.screen.CountryScreen
import com.ucb.app.crypto.presentation.screen.CryptoScreen
import com.ucb.app.dollar.presentation.screen.DollarScreen
import com.ucb.app.fakestore.presentation.screen.StoreScreen
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.map.presentation.screen.MapScreen
import com.ucb.app.movie.presentation.screen.MovieScreen
import com.ucb.app.portfolio.presentation.screen.PortfolioScreen
import com.ucb.app.remoteconfig.presentation.screen.RemoteConfigScreen
import com.ucb.app.example.presentation.screen.DesignSystemExampleScreen
import com.ucb.app.analytics.presentation.screen.AnalyticsScreen
import com.ucb.app.form.presentation.screen.FormScreen

// fun AppNavhost(startDest: NavRoute = NavRoute.DesignSystemExample) // notificacion redireccion
@Composable
fun AppNavHost(startDest: NavRoute = NavRoute.Form) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDest) {
        composable<NavRoute.Profile> {

        }

        composable<NavRoute.ProfileEdit> {

        }
        composable<NavRoute.Github> {
            GithubScreen()  // Consumo de API
        }
        composable<NavRoute.Movies> {
            MovieScreen()   // Consumo de API
        }
        composable<NavRoute.Crypto> {
            CryptoScreen()
        }
        composable<NavRoute.FakeStore> {
            StoreScreen()
        }
        composable<NavRoute.CountryStore> {
            CountryScreen() // Consumo de API (primer examen)
        }
        composable<NavRoute.Dollar> {
            DollarScreen()  // Uso de base de datos local (room)
        }
        composable<NavRoute.Map> {
            MapScreen()     // Uso de permisos de ubicacion
        }
        composable<NavRoute.Portfolio> {
            PortfolioScreen()   // Base de datos remota (firebase) y traducciones con loco
        }
        composable<NavRoute.Config> {
            RemoteConfigScreen() // Uso de remote config (firebase)
        }
        composable<NavRoute.DesignSystemExample> {
            DesignSystemExampleScreen() // Nuevo modulo DesignSystem
        }
        composable<NavRoute.Analytics> {
            AnalyticsScreen()
        }
        composable<NavRoute.Form> {
            FormScreen()
        }
    }
}
