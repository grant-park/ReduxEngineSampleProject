package ai.grant.reduxenginesampleproject.model.reducers

import ai.grant.reduxengine.core.Action
import ai.grant.reduxengine.core.Reducer
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.model.actions.NavigationAction
import ai.grant.reduxenginesampleproject.ui.components.EditNoteView
import ai.grant.reduxenginesampleproject.ui.components.NewNoteView
import ai.grant.reduxenginesampleproject.ui.components.NotesView
import ai.grant.reduxenginesampleproject.ui.components.StartUpView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NavigationStateReducer : Reducer<AppState> {
    override fun reduce(action: Action, state: AppState): AppState {
        return when (action) {
            is NavigationAction.GoToStartUpView -> {
                state.copy(currentView = StartUpView::class.java.name)
            }
            is NavigationAction.GoToNotesView -> {
                state.copy(currentView = NotesView::class.java.name)
            }
            is NavigationAction.GoToNewNote -> {
                state.copy(currentView = NewNoteView::class.java.name)
            }
            is NavigationAction.GoToEditingView -> {
                state.copy(
                    currentView = EditNoteView::class.java.name,
                    currentEditId = action.noteId
                )
            }
            else -> state
        }
    }
}