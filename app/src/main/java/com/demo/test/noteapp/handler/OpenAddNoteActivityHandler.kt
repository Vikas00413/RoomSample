package com.demo.test.noteapp.handler

import android.content.Context
import android.content.Intent
import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.view.activity.AddUpdateNoteActivity

/**
 * This is handler class open AddUpdateNoteActivity
 */
class OpenAddNoteActivityHandler(var context: Context) {
    /**
     * this method is use for AddUpdateNoteActivity
     */
    fun onClickAdd(){
        val intent = Intent(context, AddUpdateNoteActivity::class.java)
        (context as MainActivity).startActivityForResult(intent, MainActivity.NEW_NOTE_ACTIVITY_REQUEST_CODE)
    }

}