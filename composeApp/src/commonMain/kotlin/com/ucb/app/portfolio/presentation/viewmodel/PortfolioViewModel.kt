package com.ucb.app.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.portfolio.domain.usecase.GetPortfolioUseCase
import com.ucb.app.portfolio.domain.usecase.SavePortfolioUseCase
import com.ucb.app.portfolio.presentation.state.PortfolioEffect
import com.ucb.app.portfolio.presentation.state.PortfolioEvent
import com.ucb.app.portfolio.presentation.state.PortfolioUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PortfolioViewModel(
    val getPortfolioUseCase: GetPortfolioUseCase,
    val savePortfolioUseCase: SavePortfolioUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PortfolioUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<PortfolioEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: PortfolioEvent) {
        when (event) {
            PortfolioEvent.OnLoadBalance -> loadBalance()
            is PortfolioEvent.OnAmountChanged -> {
                _state.update { it.copy(amountToAdd = event.value) }
            }
            PortfolioEvent.OnAddFundsClick -> addFunds()
        }
    }

    private fun loadBalance() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                getPortfolioUseCase.invoke().collect { model ->
                    _state.update { it.copy(model = model, isLoading = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                _effect.send(PortfolioEffect.ShowError(e.message ?: "Error al cargar"))
            }
        }
    }

    private fun addFunds() {
        val amount = _state.value.amountToAdd.toDoubleOrNull()
        if (amount != null && amount > 0) {
            _state.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                try {
                    val currentBalance = _state.value.model.balance
                    savePortfolioUseCase.invoke(currentBalance + amount)
                    _state.update { it.copy(amountToAdd = "", isLoading = false) }
                    _effect.send(PortfolioEffect.ShowSuccess)
                } catch (e: Exception) {
                    _state.update { it.copy(isLoading = false) }
                    _effect.send(PortfolioEffect.ShowError(e.message ?: "Error al guardar"))
                }
            }
        } else {
            viewModelScope.launch {
                _effect.send(PortfolioEffect.ShowError("Monto inválido"))
            }
        }
    }
}
