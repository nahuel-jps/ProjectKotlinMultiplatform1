package com.ucb.app.portfolio.domain.usecase

import com.ucb.app.portfolio.domain.model.PortfolioModel
import com.ucb.app.portfolio.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

class GetPortfolioUseCase(
    val repository: PortfolioRepository
) {
    suspend fun invoke(): Flow<PortfolioModel> {
        return repository.getBalance()
    }
}
