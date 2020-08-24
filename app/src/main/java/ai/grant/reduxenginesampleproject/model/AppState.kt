package ai.grant.reduxenginesampleproject.model

import ai.grant.reduxengine.core.State
import ai.grant.reduxenginesampleproject.ui.components.StartUpView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.Serializable

@Serializable
@ExperimentalCoroutinesApi
data class AppState constructor(
    val notes: List<Note> = emptyList(),
    val currentView: String = StartUpView::class.java.name,
    val currentEditId: Int? = null
) : State

@Serializable
data class Note(
    val noteId: Int,
    val content: String
)
