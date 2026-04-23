package com.ucb.app.portfolio.domain.usecase

import com.ucb.app.portfolio.domain.repository.PortfolioRepository

class SavePortfolioUseCase(
    val repository: PortfolioRepository
) {
    suspend fun invoke(balance: Double) {
        repository.saveBalance(balance)
    }
}
