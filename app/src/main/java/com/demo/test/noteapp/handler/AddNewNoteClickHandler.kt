package com.demo.test.noteapp.handler

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.demo.test.noteapp.adapter.NoteListAdapter
import com.demo.test.noteapp.database.Note
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
                resultIntent.putExtra(Constant.PURPOSE, Constant.UPDATE)
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

    // Method to show an alert dialog with yes, no and cancel button
    fun delete(data: NoteData){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(context)

        // Set a title for alert dialog
        builder.setTitle("Are you sure to delete?")

        // Set a message for alert dialog
        builder.setMessage("This is a sample message of AlertDialog.")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE ->{
                    val resultIntent = Intent()
                    resultIntent.putExtra(Constant.NOTE, data.description)
                    resultIntent.putExtra(Constant.TITLE, data.title)
                    resultIntent.putExtra(Constant.DATE, data.date)
                    resultIntent.putExtra(Constant.NOTE_ID, data.noteid!!)
                    resultIntent.putExtra(Constant.PURPOSE, Constant.DELETE)
                    (context as NewNoteActivity).setResult(Activity.RESULT_OK, resultIntent)
                    (context as NewNoteActivity).finish()
                }
                DialogInterface.BUTTON_NEGATIVE ->{
                    dialog.cancel()

                }

            }
        }


        // Set the alert dialog positive/yes button
        builder.setPositiveButton("Delete",dialogClickListener)

        // Set the alert dialog negative/no button
        builder.setNegativeButton("Cancel",dialogClickListener)




        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }


}