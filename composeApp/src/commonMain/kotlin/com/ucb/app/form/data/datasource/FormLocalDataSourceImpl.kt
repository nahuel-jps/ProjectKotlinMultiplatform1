package com.ucb.app.form.data.datasource

import com.ucb.app.form.data.dao.FormDraftDao
import com.ucb.app.form.data.entity.FormDraftEntity

class FormLocalDataSourceImpl(
    private val dao: FormDraftDao
) : FormLocalDataSource {
    override suspend fun saveDraft(entity: FormDraftEntity) {
        dao.saveDraft(entity)
    }

    override suspend fun getDraft(): FormDraftEntity? {
        return dao.getDraft()
    }
}
