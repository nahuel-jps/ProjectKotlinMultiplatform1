package com.ucb.app.form.data.repository

import com.ucb.app.form.data.datasource.FormLocalDataSource
import com.ucb.app.form.data.datasource.FormRemoteDataSource
import com.ucb.app.form.data.entity.toEntity
import com.ucb.app.form.domain.model.FormDraft
import com.ucb.app.form.domain.repository.FormDraftRepository

class FormDraftRepositoryImpl(
    private val localDataSource: FormLocalDataSource,
    private val remoteDataSource: FormRemoteDataSource
) : FormDraftRepository {

    override suspend fun saveDraft(draft: FormDraft) {
        try {
            localDataSource.saveDraft(draft.toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun loadDraft(): FormDraft? {
        return try {
            localDataSource.getDraft()?.toModel()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun submitForm(draft: FormDraft) {
        try {
            remoteDataSource.submitForm(
                name = draft.name,
                email = draft.email,
                message = draft.message,
                timestamp = draft.lastSavedAt
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
