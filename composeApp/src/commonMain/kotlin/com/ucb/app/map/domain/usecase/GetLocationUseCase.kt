package com.ucb.app.map.domain.usecase

import com.ucb.app.map.domain.model.LocationModel
import com.ucb.app.map.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase(
    val repository: LocationRepository
) {
    fun invoke(): Flow<LocationModel> {
        return repository.getLocationUpdates()
    }
}
