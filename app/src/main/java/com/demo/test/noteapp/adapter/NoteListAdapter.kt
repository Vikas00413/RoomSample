package com.demo.test.noteapp.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.test.noteapp.R
import com.demo.test.noteapp.adapter.NoteListAdapter.NoteViewHolder
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.databinding.NoteBinding
import com.demo.test.noteapp.handler.NoteRowClickHandler


/**
 * This is adapter class is use for show all notes as list form
 */
class NoteListAdapter(val mContext: Context) : RecyclerView.Adapter<NoteViewHolder>() {



    private var mNotes: List<Note>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)

        var noteBinding: NoteBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                parent,
                false

            )
        var handler=NoteRowClickHandler(mContext)

        noteBinding.handler=handler

        return NoteViewHolder(noteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (mNotes != null) {
            val note = mNotes!![position]
            holder.bind(note)
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




    }

}