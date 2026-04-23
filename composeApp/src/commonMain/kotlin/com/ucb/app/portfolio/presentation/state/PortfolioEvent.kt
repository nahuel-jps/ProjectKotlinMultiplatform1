package com.ucb.app.portfolio.presentation.state

sealed interface PortfolioEvent {
    object OnLoadBalance : PortfolioEvent
    data class OnAmountChanged(val value: String) : PortfolioEvent
    object OnAddFundsClick : PortfolioEvent
}
