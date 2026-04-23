package com.ucb.app.form.domain.usecase

import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.repository.FormDraftRepository

class SubmitFormUseCase(
    private val repository: FormDraftRepository
) {
    suspend fun invoke(draft: FormDraft) {
        repository.submitForm(draft)
    }
}
