package com.demo.test.noteapp.handler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.demo.test.noteapp.model.NoteData
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.view.activity.NewNoteActivity

class AddNewNoteClickHandler(var context: Context) {

    fun onClickAddNewNote(data:NoteData){

    var note=data.description
    var date=data.date
    var titile=data.title



        if (TextUtils.isEmpty(date)) {
            Toast.makeText(context, "Please enter date", Toast.LENGTH_LONG ).show()

        }
        else if (TextUtils.isEmpty(titile)) {
            Toast.makeText(context, "Please enter title", Toast.LENGTH_LONG ).show()
        }
        else if (TextUtils.isEmpty(note)) {
            Toast.makeText(context, "Please enter title", Toast.LENGTH_LONG ).show()
        } else {

            if(data.noteid==null) {
                val resultIntent = Intent()

                resultIntent.putExtra(Constant.NOTE, data.description)
                resultIntent.putExtra(Constant.TITLE, data.title)
                resultIntent.putExtra(Constant.DATE, data.date)
                (context as NewNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                (context as NewNoteActivity).finish()
            }else{
                val resultIntent = Intent()
                resultIntent.putExtra(Constant.NOTE, data.description)
                resultIntent.putExtra(Constant.TITLE, data.title)
                resultIntent.putExtra(Constant.DATE, data.date)
                resultIntent.putExtra(Constant.NOTE_ID, data.noteid!!)
                (context as NewNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                (context as NewNoteActivity).finish()
            }
        }

    }
    fun cancelUpdate(){
        val returnIntent = Intent()
        (context as NewNoteActivity).setResult(Activity.RESULT_CANCELED, returnIntent)
        (context as NewNoteActivity).finish()
    }


}