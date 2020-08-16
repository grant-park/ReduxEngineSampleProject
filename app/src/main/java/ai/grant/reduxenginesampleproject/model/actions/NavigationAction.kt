package ai.grant.reduxenginesampleproject.model.actions

import ai.grant.reduxengine.core.Action

sealed class NavigationAction : Action {
    data class GoToEditingView(val noteId: Int) : NavigationAction()

    object GoToNotesView : NavigationAction()

    object GoToNewNote : NavigationAction()

    object GoToStartUpView : NavigationAction()
}
