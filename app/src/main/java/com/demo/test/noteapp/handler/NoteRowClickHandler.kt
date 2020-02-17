package com.demo.test.noteapp.handler

import android.content.Context
import android.content.Intent
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.view.activity.AddUpdateNoteActivity

/**
 * This is handler class for open AddUpdateNoteActivity
 */
class NoteRowClickHandler(var mContext: Context){

    /**
     * this method is open AddUpdateNoteActivity
     */
    fun update(note: Note){
        val intent = Intent(mContext, AddUpdateNoteActivity::class.java)
        intent.putExtra("note_id", note.id)
        intent.putExtra(Constant.PURPOSE, Constant.UPDATE)

        (mContext as MainActivity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
    }



}