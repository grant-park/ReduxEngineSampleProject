package ai.grant.reduxenginesampleproject.ui.screens

import ai.grant.reduxengine.INSTANCE.dispatch
import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.model.actions.NavigationAction
import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class StartUpView(context: Context) : BaseView(R.layout.startup, context) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        dispatch(NavigationAction.GoToNotesView)
    }
}
