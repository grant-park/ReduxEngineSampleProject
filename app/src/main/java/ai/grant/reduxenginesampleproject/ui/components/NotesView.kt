package ai.grant.reduxenginesampleproject.ui.components

import ai.grant.reduxengine.INSTANCE.dispatch
import ai.grant.reduxengine.INSTANCE.listen
import ai.grant.reduxengine.extensions.addTo
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.model.Note
import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.model.actions.NavigationAction
import ai.grant.reduxenginesampleproject.model.actions.NoteAction
import android.content.Context
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notes.view.button
import kotlinx.android.synthetic.main.notes.view.emptyView
import kotlinx.android.synthetic.main.notes.view.recyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NotesView(context: Context) : BaseView(R.layout.notes, context) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NotesAdapter(emptyList(), ::onItemSelected, ::onItemDeleteAttempted)
        recyclerView.adapter = adapter
        button.setOnClickListener {
            dispatch(NavigationAction.GoToNewNote)
        }

        emptyView.visibility = View.INVISIBLE
        listen<AppState, List<Note>>({ it.notes }) {
            if (it.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            }
            TransitionManager.beginDelayedTransition(this, Fade())
            adapter.updateNotes(it)
        }.addTo(disposables)
    }

    private fun onItemSelected(position: Int) {
        dispatch(NavigationAction.GoToEditingView(position))
    }

    private fun onItemDeleteAttempted(position: Int) {
        showDialog(
            message = "Are you sure you want to delete?",
            leftButtonText = "OK",
            rightButtonText = "CANCEL",
        ) {
            if (it) dispatch(NoteAction.DeleteNote(position))
        }
    }
}
