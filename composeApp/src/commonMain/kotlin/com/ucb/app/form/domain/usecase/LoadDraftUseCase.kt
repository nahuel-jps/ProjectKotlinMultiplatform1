package com.ucb.app.form.domain.usecase

import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.repository.FormDraftRepository

class LoadDraftUseCase(
    private val repository: FormDraftRepository
) {
    suspend fun invoke(): FormDraft? {
        return repository.loadDraft()
    }
}
