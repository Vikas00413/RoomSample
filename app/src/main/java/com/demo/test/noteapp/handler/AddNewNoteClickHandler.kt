package com.demo.test.noteapp.handler

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.demo.test.noteapp.model.NoteData
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.view.activity.AddUpdateNoteActivity

/**
 * This is handler class perform add new note,delete note,cancel note
 */
class AddNewNoteClickHandler(var context: Context) {


    /**
     * this method is for add new note,update note
     */
    fun onClickAddNewNote(data: NoteData) {

        var note = data.description
        var date = data.date
        var titile = data.title

        if (TextUtils.isEmpty(date)) {
            Toast.makeText(context, "Please enter date", Toast.LENGTH_LONG).show()

        } else if (TextUtils.isEmpty(titile)) {
            Toast.makeText(context, "Please enter title", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(note)) {
            Toast.makeText(context, "Please fill your note", Toast.LENGTH_LONG).show()
        } else {

            if (data.noteid == null) {
                val resultIntent = Intent()

                resultIntent.putExtra(Constant.NOTE, data.description)
                resultIntent.putExtra(Constant.TITLE, data.title)
                resultIntent.putExtra(Constant.DATE, data.date)
                (context as AddUpdateNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                (context as AddUpdateNoteActivity).finish()
            } else {
                val resultIntent = Intent()
                resultIntent.putExtra(Constant.NOTE, data.description)
                resultIntent.putExtra(Constant.TITLE, data.title)
                resultIntent.putExtra(Constant.DATE, data.date)
                resultIntent.putExtra(Constant.NOTE_ID, data.noteid!!)
                resultIntent.putExtra(Constant.PURPOSE, Constant.UPDATE)
                (context as AddUpdateNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                (context as AddUpdateNoteActivity).finish()
            }
        }

    }

    /**
     * this method is use for cancel update
     */
    fun cancelUpdate() {
        val returnIntent = Intent()
        (context as AddUpdateNoteActivity).setResult(Activity.RESULT_CANCELED, returnIntent)
        (context as AddUpdateNoteActivity).finish()
    }

    /**
     * this method is for delete note from tabel
     */
    fun delete(data: NoteData) {
        lateinit var dialog: AlertDialog
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Note?")
        builder.setMessage("Are you sure to delete?")
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val resultIntent = Intent()
                    resultIntent.putExtra(Constant.NOTE, data.description)
                    resultIntent.putExtra(Constant.TITLE, data.title)
                    resultIntent.putExtra(Constant.DATE, data.date)
                    resultIntent.putExtra(Constant.NOTE_ID, data.noteid!!)
                    resultIntent.putExtra(Constant.PURPOSE, Constant.DELETE)
                    (context as AddUpdateNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                    (context as AddUpdateNoteActivity).finish()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    dialog.cancel()

                }

            }
        }
        builder.setPositiveButton("Delete", dialogClickListener)
        builder.setNegativeButton("Cancel", dialogClickListener)
        dialog = builder.create()
        dialog.show()
    }


}