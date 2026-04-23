package com.ucb.app.portfolio.data.repository

import com.ucb.app.portfolio.data.datasource.FirebaseManager
import com.ucb.app.portfolio.domain.model.PortfolioModel
import com.ucb.app.portfolio.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PortfolioRepositoryImpl(
    val firebaseManager: FirebaseManager
) : PortfolioRepository {
    override suspend fun saveBalance(balance: Double) {
        firebaseManager.saveData("currency", balance.toString())
    }

    override suspend fun getBalance(): Flow<PortfolioModel> {
        return firebaseManager.getCurrency().map {
            PortfolioModel(balance = it.toDoubleOrNull() ?: 0.0)
        }
    }
}
