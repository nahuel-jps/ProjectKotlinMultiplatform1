package com.ucb.app

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import android.Manifest
import com.google.firebase.messaging.FirebaseMessaging
import com.ucb.app.core.worker.LogScheduler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.app.analytics.worker.AnalyticsScheduler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Pedir permiso de notificaciones para Android 13+ (API 33)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }

        // Comentado temporalmente a petición del usuario para no forzar la ruta de inicio, notificacion redireccion
        /*
        var startingRoute: NavRoute = NavRoute.Config // notificacion redireccion
        var startingRoute: NavRoute? = null
        val targetScreen = intent?.extras?.getString("target_screen")
        if (targetScreen == "github") {
            Log.d("FCM_INTENT", "Redirigiendo a Github por notificación Push")
            startingRoute = NavRoute.Github
        }
        */

        // --- WorkManager ---
        // Programar sincronizaciones/actividades sigilosas en segundo plano
        LogScheduler(this).schedulePeriodicaUpload()

        // Obtener e imprimir el Token actual de FCM en Logcat para pruebas en consola
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FIREBASE_FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("FIREBASE_FCM", "TU TOKEN FCM DE PRUEBA ES:\n$token")
            // También mostramos un mensajito rápido en pantalla para no tener que buscar ciegamente
            Toast.makeText(baseContext, "FCM Token copiado internamente, revisa el Logcat", Toast.LENGTH_SHORT).show()
        }

        setContent {
            // App(startRoute = startingRoute)
            App() // Ahora ignora las notificaciones y usa siempre el default de AppNavHost
        }
    }

    override fun onStart() {
        super.onStart()
        AnalyticsScheduler(this).scheduleLogEvent("APP_OPEN")
    }

    override fun onStop() {
        super.onStop()
        AnalyticsScheduler(this).scheduleLogEvent("APP_CLOSE")
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}