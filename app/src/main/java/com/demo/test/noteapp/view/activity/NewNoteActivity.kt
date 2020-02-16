package com.demo.test.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.demo.test.noteapp.R

class NewNoteActivity : AppCompatActivity() {
    private var etNewNote: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        etNewNote = findViewById(R.id.etNewNote)
        val button = findViewById<Button>(R.id.bAdd)
        button.setOnClickListener {
            val resultIntent = Intent()
            if (TextUtils.isEmpty(etNewNote!!.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                val note = etNewNote!!.text.toString()
                resultIntent.putExtra(NOTE_ADDED, note)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }
    }

    companion object {
        const val NOTE_ADDED = "new_note"
    }
}