package com.ucb.app.portfolio.domain.repository

import com.ucb.app.portfolio.domain.model.PortfolioModel
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun saveBalance(balance: Double)
    suspend fun getBalance(): Flow<PortfolioModel>

}
