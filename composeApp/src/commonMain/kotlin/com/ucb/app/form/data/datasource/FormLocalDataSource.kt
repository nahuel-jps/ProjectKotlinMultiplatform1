package com.ucb.app.form.data.datasource

import com.ucb.app.form.data.entity.FormDraftEntity

interface FormLocalDataSource {
    suspend fun saveDraft(entity: FormDraftEntity)
    suspend fun getDraft(): FormDraftEntity?
}
