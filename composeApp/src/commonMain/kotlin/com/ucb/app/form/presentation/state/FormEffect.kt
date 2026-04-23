package com.ucb.app.form.presentation.state

sealed interface FormEffect {
    object SubmitSuccess : FormEffect
    data class ShowError(val message: String) : FormEffect
    object DraftSaved : FormEffect
}
