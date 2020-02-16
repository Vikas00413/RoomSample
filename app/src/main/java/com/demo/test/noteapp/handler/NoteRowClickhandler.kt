package com.demo.test.noteapp.handler

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import com.demo.test.noteapp.adapter.NoteListAdapter
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.view.activity.NewNoteActivity

class NoteRowClickhandler(var mContext: Context){


    fun update(note: Note){
        val intent = Intent(mContext, NewNoteActivity::class.java)
        intent.putExtra("note_id", note.id)
        intent.putExtra(Constant.PURPOSE, Constant.UPDATE)

        (mContext as MainActivity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
    }

    // Method to show an alert dialog with yes, no and cancel button
     fun delete(note:Note,listner: NoteListAdapter.OnDeleteClickListener){
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog


        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(mContext)

        // Set a title for alert dialog
        builder.setTitle("Are you sure to delete?")

        // Set a message for alert dialog
        builder.setMessage("This is a sample message of AlertDialog.")


        // On click listener for dialog buttons
        val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE ->{
                    listner?.OnDeleteClickListener(note)
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