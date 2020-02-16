package com.demo.test.noteapp.handler

import android.content.Context
import android.content.Intent
import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.view.activity.NewNoteActivity

class OpenAddNoteActivityHandler(var context: Context) {

    fun onClickAdd(){
        val intent = Intent(context, NewNoteActivity::class.java)
        (context as MainActivity).startActivityForResult(intent, MainActivity.NEW_NOTE_ACTIVITY_REQUEST_CODE)
    }

}