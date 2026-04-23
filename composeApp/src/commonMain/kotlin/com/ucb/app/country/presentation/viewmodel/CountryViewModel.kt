package com.ucb.app.country.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.country.domain.usecase.GetCountriesUseCase
import com.ucb.app.country.presentation.state.CountryEffect
import com.ucb.app.country.presentation.state.CountryEvent
import com.ucb.app.country.presentation.state.CountryState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel(
    val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountryState())
    val state = _state.asStateFlow()

    private val _effect = Channel<CountryEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: CountryEvent) {
        when (event) {
            CountryEvent.OnLoad -> loadCountries()
            CountryEvent.OnRefresh -> loadCountries()
        }
    }

    private fun loadCountries() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val countries = getCountriesUseCase.invoke()
                _state.update {
                    it.copy(
                        isLoading = false,
                        countries = countries
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
                _effect.send(CountryEffect.ShowError(e.message ?: "Error desconocido"))
            }
        }
    }
}