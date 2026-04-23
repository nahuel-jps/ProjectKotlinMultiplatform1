package com.ucb.app.portfolio.presentation.state

import com.ucb.app.portfolio.domain.model.PortfolioModel

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val model: PortfolioModel = PortfolioModel(),
    val amountToAdd: String = ""
)
