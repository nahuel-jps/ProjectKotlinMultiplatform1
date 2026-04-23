package com.ucb.app.form.domain.usecase

import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.repository.FormDraftRepository

class SaveDraftUseCase(
    private val repository: FormDraftRepository
) {
    suspend fun invoke(draft: FormDraft) {
        repository.saveDraft(draft)
    }
}
