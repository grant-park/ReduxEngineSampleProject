package ai.grant.reduxenginesampleproject.ui.components

import ai.grant.reduxengine.INSTANCE.dispatch
import ai.grant.reduxengine.INSTANCE.listen
import ai.grant.reduxengine.extensions.addTo
import ai.grant.reduxenginesampleproject.model.AppState
import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.model.actions.NavigationAction
import ai.grant.reduxenginesampleproject.model.actions.NoteAction
import android.content.Context
import kotlinx.android.synthetic.main.new_note.view.cancelButton
import kotlinx.android.synthetic.main.new_note.view.doneButton
import kotlinx.android.synthetic.main.new_note.view.editText
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class EditNoteView(context: Context) : BaseView(R.layout.new_note, context) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText.requestFocus()
        toggleSoftInput(true)
        listen<AppState> { appState ->
            editText.setText(appState.notes.find {
                appState.currentEditId!! == it.noteId
            }!!.content)
            doneButton.setOnClickListener {
                if (editText.text.toString().isNotBlank()) {
                    dispatch(
                        NoteAction.EditNote(
                            appState.currentEditId!!,
                            editText.text.toString()
                        )
                    )
                }
                exit()
            }
        }.addTo(disposables)
        cancelButton.setOnClickListener {
            exit()
        }
    }

    private fun exit() {
        toggleSoftInput(false)
        dispatch(NavigationAction.GoToNotesView)
    }
}