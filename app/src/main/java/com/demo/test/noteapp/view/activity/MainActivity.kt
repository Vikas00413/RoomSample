package com.demo.test.noteapp.view.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import android.widget.Toast

import com.demo.test.noteapp.R
import com.demo.test.noteapp.adapter.NoteListAdapter
import com.demo.test.noteapp.adapter.NoteListAdapter.OnDeleteClickListener
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.viewmodel.NoteViewModel
import java.util.*

class MainActivity : AppCompatActivity(), OnDeleteClickListener {
    private val TAG = this.javaClass.simpleName
    private var noteViewModel: NoteViewModel? = null
    private var noteListAdapter: NoteListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        noteListAdapter = NoteListAdapter(this, this)
        recyclerView.adapter = noteListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel!!.allNotes!!.observe(this, android.arch.lifecycle.Observer { notes -> noteListAdapter!!.setNotes(notes!!) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) { // Code to insert note
            val note_id = UUID.randomUUID().toString()
            val note = Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel!!.insert(note)
            Toast.makeText(
                    applicationContext,
                    R.string.saved,
                    Toast.LENGTH_LONG).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) { // Code to update the note
            val note = Note(
                    data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATED_NOTE))
            noteViewModel!!.update(note)
            Toast.makeText(
                    applicationContext,
                    R.string.updated,
                    Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun OnDeleteClickListener(myNote: Note?) { // Code for Delete operation
        noteViewModel!!.delete(myNote)
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }

}