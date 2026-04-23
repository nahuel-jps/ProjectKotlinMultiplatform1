package com.ucb.app.form.domain.model

data class FormDraft(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val lastSavedAt: Long = 0L,
    val isSubmitted: Boolean = false
)
