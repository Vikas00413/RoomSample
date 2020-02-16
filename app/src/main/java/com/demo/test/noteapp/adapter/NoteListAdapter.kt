package com.demo.test.noteapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.demo.test.noteapp.R
import com.demo.test.noteapp.adapter.NoteListAdapter.NoteViewHolder
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.view.activity.EditNoteActivity
import com.demo.test.noteapp.view.activity.MainActivity
import java.util.*

class NoteListAdapter(context: Context, listener: OnDeleteClickListener?) : RecyclerView.Adapter<NoteViewHolder>() {
    interface OnDeleteClickListener {
        fun OnDeleteClickListener(myNote: Note?)
    }


    private val layoutInflater: LayoutInflater
    private val mContext: Context
    private var mNotes: List<Note>? = null
    private val onDeleteClickListener: OnDeleteClickListener?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (mNotes != null) {
            val note = mNotes!![position]
            holder.setData(note.note, position)
            holder.setListeners()
        } else { // Covers the case of data not being ready yet.
            holder.noteItemView.setText(R.string.no_note)
        }
    }

    override fun getItemCount(): Int {
        return if (mNotes != null) mNotes!!.size else 0
    }

    fun setNotes(notes: List<Note>) {
        mNotes = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView
        private var mPosition = 0
        private val imgDelete: ImageView
        private val imgEdit: ImageView
        fun setData(note: String?, position: Int) {
            noteItemView.text = note
            mPosition = position
        }

        fun setListeners() {
            imgEdit.setOnClickListener {
                val intent = Intent(mContext, EditNoteActivity::class.java)
                intent.putExtra("note_id", mNotes!![mPosition].id)
                (mContext as Activity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
            imgDelete.setOnClickListener { onDeleteClickListener?.OnDeleteClickListener(mNotes!![mPosition]) }
        }

        init {
            noteItemView = itemView.findViewById(R.id.txvNote)
            imgDelete = itemView.findViewById(R.id.ivRowDelete)
            imgEdit = itemView.findViewById(R.id.ivRowEdit)
        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        mContext = context
        onDeleteClickListener = listener
    }
}