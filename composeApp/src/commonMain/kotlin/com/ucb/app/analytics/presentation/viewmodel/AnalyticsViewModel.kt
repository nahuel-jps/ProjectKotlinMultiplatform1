package com.ucb.app.analytics.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ucb.app.analytics.presentation.state.AnalyticsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnalyticsViewModel : ViewModel() {
    private val _state = MutableStateFlow(AnalyticsUiState())
    val state = _state.asStateFlow()
}
