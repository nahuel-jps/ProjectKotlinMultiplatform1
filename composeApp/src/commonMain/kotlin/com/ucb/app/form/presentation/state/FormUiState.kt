package com.ucb.app.form.presentation.state

data class FormUiState(
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val isLoading: Boolean = false,
    val lastSavedAt: Long = 0L,
    val isSubmitted: Boolean = false
)
