package ai.grant.reduxenginesampleproject.ui.components

import ai.grant.reduxenginesampleproject.model.Note
import ai.grant.reduxenginesampleproject.R
import ai.grant.reduxenginesampleproject.ui.utils.DebouncedListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.thumbnail.view.deleteButton
import kotlinx.android.synthetic.main.thumbnail.view.previewText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class NotesAdapter(
    private var notes: List<Note>,
    private val onItemSelected: (Int) -> Unit,
    private val onItemDeleteAttempted: (Int) -> Unit
) : RecyclerView.Adapter<NoteViewHolder>(), CoroutineScope by MainScope() {

    fun updateNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.thumbnail, parent, false)
        )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.thumbnailView.previewText.text = notes[position].content
        holder.thumbnailView.previewText.setOnClickListener(DebouncedListener { onItemSelected(notes[position].noteId) })
        holder.thumbnailView.deleteButton.setOnClickListener(DebouncedListener {
            onItemDeleteAttempted(
                notes[position].noteId
            )
        })
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.cancel()
    }
}

