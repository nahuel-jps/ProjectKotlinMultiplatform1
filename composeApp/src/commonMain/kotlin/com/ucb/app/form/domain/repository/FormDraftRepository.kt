package com.ucb.app.form.domain.repository

import com.ucb.app.form.domain.model.FormDraft

interface FormDraftRepository {
    suspend fun saveDraft(draft: FormDraft)
    suspend fun loadDraft(): FormDraft?
    suspend fun submitForm(draft: FormDraft)
}
