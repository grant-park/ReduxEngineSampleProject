package ai.grant.reduxenginesampleproject.model.reducers

import ai.grant.reduxengine.core.Action
import ai.grant.reduxengine.core.Reducer
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.model.Note
import ai.grant.reduxenginesampleproject.model.actions.NoteAction
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NotesReducer : Reducer<AppState> {
    override fun reduce(action: Action, state: AppState): AppState {
        return when (action) {
            is NoteAction.AddNote ->
                state.copy(notes = state.notes + Note(System.currentTimeMillis().toInt(), action.content))
            is NoteAction.DeleteNote ->
                state.copy(notes = state.notes - state.notes.find { action.noteId == it.noteId }!!)
            is NoteAction.EditNote ->
                state.copy(notes = state.notes.map {
                    if (action.noteId == it.noteId) Note(it.noteId, action.content) else it
                })
            else -> state
        }
    }
}
