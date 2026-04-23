package com.ucb.app.form.presentation.state

sealed interface FormEvent {
    data class OnNameChanged(val value: String) : FormEvent
    data class OnEmailChanged(val value: String) : FormEvent
    data class OnMessageChanged(val value: String) : FormEvent
    object OnSubmitClick : FormEvent
}
