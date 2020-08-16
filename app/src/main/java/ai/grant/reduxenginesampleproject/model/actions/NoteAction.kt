package ai.grant.reduxenginesampleproject.model.actions

import ai.grant.reduxengine.core.Action

sealed class NoteAction : Action {
    data class AddNote(
        val content: String
    ) : NoteAction()

    data class DeleteNote(
        val noteId: Int
    ) : NoteAction()

    data class EditNote(
        val noteId: Int,
        val content: String
    ) : NoteAction()
}
