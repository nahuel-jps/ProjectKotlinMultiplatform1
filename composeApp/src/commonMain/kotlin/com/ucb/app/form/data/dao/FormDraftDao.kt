package com.ucb.app.form.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucb.app.form.data.entity.FormDraftEntity

@Dao
interface FormDraftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDraft(draft: FormDraftEntity)

    @Query("SELECT * FROM form_draft WHERE id = 1 LIMIT 1")
    suspend fun getDraft(): FormDraftEntity?
}
