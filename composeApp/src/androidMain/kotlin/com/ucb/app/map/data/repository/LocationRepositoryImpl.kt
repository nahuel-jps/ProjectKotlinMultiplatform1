package com.ucb.app.map.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ucb.app.map.domain.model.LocationModel
import com.ucb.app.map.domain.repository.LocationRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationRepositoryImpl(
    private val context: Context
) : LocationRepository {

    private val fusedClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(): Flow<LocationModel> = callbackFlow {

        val request = LocationRequest.Builder(5000L) // intervalo en ms
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { loc ->
                    trySend(LocationModel(loc.latitude, loc.longitude))
                }
            }
        }

        fusedClient.requestLocationUpdates(request, callback, null)

        awaitClose {
            fusedClient.removeLocationUpdates(callback)
        }
    }
}
