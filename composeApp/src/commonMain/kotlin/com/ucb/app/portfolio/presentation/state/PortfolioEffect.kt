package com.ucb.app.portfolio.presentation.state

sealed interface PortfolioEffect {
    data class ShowError(val message: String) : PortfolioEffect
    object ShowSuccess : PortfolioEffect
}
