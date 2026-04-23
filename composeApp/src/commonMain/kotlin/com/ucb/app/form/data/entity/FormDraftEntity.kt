package com.ucb.app.form.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucb.app.form.domain.model.FormDraft

@Entity(tableName = "form_draft")
data class FormDraftEntity(
    @PrimaryKey
    val id: Int = 1, // Solo guardamos 1 borrador (el actual)
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val lastSavedAt: Long = 0L,
    val isSubmitted: Boolean = false
) {
    fun toModel() = FormDraft(
        id = id,
        name = name,
        email = email,
        message = message,
        lastSavedAt = lastSavedAt,
        isSubmitted = isSubmitted
    )
}

fun FormDraft.toEntity() = FormDraftEntity(
    id = if (id == 0) 1 else id,
    name = name,
    email = email,
    message = message,
    lastSavedAt = lastSavedAt,
    isSubmitted = isSubmitted
)
