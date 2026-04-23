package com.ucb.app.map.domain.repository

import com.ucb.app.map.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationUpdates(): Flow<LocationModel>
}
