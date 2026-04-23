package com.ucb.app.form.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.analytics.utils.getCurrentTimeMillis
import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.usecase.LoadDraftUseCase
import com.ucb.app.form.domain.usecase.SaveDraftUseCase
import com.ucb.app.form.domain.usecase.SubmitFormUseCase
import com.ucb.app.form.presentation.state.FormEffect
import com.ucb.app.form.presentation.state.FormEvent
import com.ucb.app.form.presentation.state.FormUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormViewModel(
    private val saveDraftUseCase: SaveDraftUseCase,
    private val loadDraftUseCase: LoadDraftUseCase,
    private val submitFormUseCase: SubmitFormUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FormUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<FormEffect>()
    val effect = _effect.receiveAsFlow()

    private var autoSaveJob: Job? = null
    private val AUTO_SAVE_INTERVAL_MS = 30_000L

    init {
        loadDraft()
        startAutoSave()
    }

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.OnNameChanged -> _state.update { it.copy(name = event.value) }
            is FormEvent.OnEmailChanged -> _state.update { it.copy(email = event.value) }
            is FormEvent.OnMessageChanged -> _state.update { it.copy(message = event.value) }
            FormEvent.OnSubmitClick -> submitForm()
        }
    }

    private fun loadDraft() {
        viewModelScope.launch {
            try {
                val draft = loadDraftUseCase.invoke()
                if (draft != null) {
                    _state.update {
                        it.copy(
                            name = draft.name,
                            email = draft.email,
                            message = draft.message,
                            lastSavedAt = draft.lastSavedAt
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Corre en background del ViewModel — no bloquea la UI
    private fun startAutoSave() {
        autoSaveJob?.cancel()
        autoSaveJob = viewModelScope.launch {
            while (true) {
                delay(AUTO_SAVE_INTERVAL_MS)
                autoSaveToRoom()
            }
        }
    }

    private suspend fun autoSaveToRoom() {
        val current = _state.value
        if (current.name.isBlank() && current.email.isBlank() && current.message.isBlank()) return
        try {
            val draft = FormDraft(
                name = current.name,
                email = current.email,
                message = current.message,
                lastSavedAt = getCurrentTimeMillis()
            )
            saveDraftUseCase.invoke(draft)
            _state.update { it.copy(lastSavedAt = draft.lastSavedAt) }
            _effect.send(FormEffect.DraftSaved)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun submitForm() {
        val current = _state.value
        if (current.name.isBlank() || current.email.isBlank() || current.message.isBlank()) {
            viewModelScope.launch {
                _effect.send(FormEffect.ShowError("Por favor completa todos los campos"))
            }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val draft = FormDraft(
                    name = current.name,
                    email = current.email,
                    message = current.message,
                    lastSavedAt = getCurrentTimeMillis(),
                    isSubmitted = true
                )
                // Guarda en Room y sincroniza a Firebase
                saveDraftUseCase.invoke(draft)
                submitFormUseCase.invoke(draft)
                _state.update { it.copy(isLoading = false, isSubmitted = true) }
                _effect.send(FormEffect.SubmitSuccess)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                _effect.send(FormEffect.ShowError(e.message ?: "Error al enviar"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        autoSaveJob?.cancel()
    }
}
