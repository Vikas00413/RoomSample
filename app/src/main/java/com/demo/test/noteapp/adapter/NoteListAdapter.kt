package com.demo.test.noteapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.demo.test.noteapp.R
import com.demo.test.noteapp.adapter.NoteListAdapter.NoteViewHolder
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.databinding.NoteBinding
import com.demo.test.noteapp.handler.NoteRowClickhandler
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.view.activity.EditNoteActivity
import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.view.activity.NewNoteActivity
import java.util.*

class NoteListAdapter(context: Context, listener: OnDeleteClickListener?) : RecyclerView.Adapter<NoteViewHolder>() {
    interface OnDeleteClickListener {
        fun OnDeleteClickListener(myNote: Note?)
    }


    private val mContext: Context
    private var mNotes: List<Note>? = null
    private val onDeleteClickListener: OnDeleteClickListener?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)

        var noteBinding: NoteBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                parent,
                false

            )
        var handler=NoteRowClickhandler(mContext)

        noteBinding.handler=handler

noteBinding.listner=onDeleteClickListener
        return NoteViewHolder(noteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (mNotes != null) {
            val note = mNotes!![position]
           // holder.setData(note.note, position)
            holder.bind(note)
           // holder.setListeners()
        }
    }

    override fun getItemCount(): Int {
        return if (mNotes != null) mNotes!!.size else 0
    }

    fun setNotes(notes: List<Note>) {
        mNotes = notes
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(var noteBinding: NoteBinding) : RecyclerView.ViewHolder(noteBinding.root) {

        fun bind(dataModel: Note) {
            this.noteBinding.data = dataModel
            this.noteBinding.executePendingBindings()
        }

        fun setListeners() {
            noteBinding.ivRowEdit.setOnClickListener {
                val intent = Intent(mContext, NewNoteActivity::class.java)
                intent.putExtra("note_id", mNotes!![layoutPosition].id)
                intent.putExtra(Constant.PURPOSE,Constant.UPDATE)

                (mContext as MainActivity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
            noteBinding.ivRowDelete.setOnClickListener { onDeleteClickListener?.OnDeleteClickListener(mNotes!![layoutPosition]) }
        }


    }

    init {

        mContext = context
        onDeleteClickListener = listener
    }
}