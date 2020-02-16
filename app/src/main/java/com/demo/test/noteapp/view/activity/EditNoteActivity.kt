package com.demo.test.noteapp.view.activity

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

import com.demo.test.noteapp.R
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.viewmodel.EditNoteViewModel

class EditNoteActivity : AppCompatActivity() {
    private var etNote: EditText? = null
    private var bundle: Bundle? = null
    private var noteId: String? = null
    private var note: LiveData<Note>? = null
    var noteModel: EditNoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        etNote = findViewById(R.id.etNote)
        bundle = intent.extras
        if (bundle != null) {
            noteId = bundle!!.getString("note_id")
        }
        noteModel = ViewModelProviders.of(this).get(EditNoteViewModel::class.java)
        note = noteModel!!.getNote(noteId)
        note!!.observe(this, Observer { note -> etNote!!.setText(note!!.note) })
    }

    fun updateNote(view: View?) {
        val updatedNote = etNote!!.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra(NOTE_ID, noteId)
        resultIntent.putExtra(UPDATED_NOTE, updatedNote)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun cancelUpdate(view: View?) {
        finish()
    }

    companion object {
        const val NOTE_ID = "note_id"
        const val UPDATED_NOTE = "note_text"
    }
}