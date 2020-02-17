package com.demo.test.noteapp.view.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.Toolbar

import android.widget.Toast

import com.demo.test.noteapp.R
import com.demo.test.noteapp.adapter.NoteListAdapter
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.databinding.ActivityMainBinding
import com.demo.test.noteapp.handler.OpenAddNoteActivityHandler
import com.demo.test.noteapp.util.Constant
import com.demo.test.noteapp.viewmodel.NoteCrudOperationsViewModel
import java.util.*

/**
 * This class is for show all notes in list form
 */
class MainActivity : AppCompatActivity(){
    private val TAG = this.javaClass.simpleName
    private var noteViewModel: NoteCrudOperationsViewModel? = null
    private var noteListAdapter: NoteListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        noteListAdapter = NoteListAdapter(this)
        var handler= OpenAddNoteActivityHandler(this)
        binding.adapter=noteListAdapter
        binding.handler=handler

        noteViewModel = ViewModelProviders.of(this).get(NoteCrudOperationsViewModel::class.java)
        noteViewModel!!.allNotes!!.observe(this, android.arch.lifecycle.Observer { notes -> noteListAdapter!!.setNotes(notes!!) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && data!=null) {
            when (requestCode) {
                NEW_NOTE_ACTIVITY_REQUEST_CODE -> { // Code to insert note


                    val note_id = UUID.randomUUID().toString()
                    var date = data.getStringExtra(Constant.DATE)
                    var desc = data.getStringExtra(Constant.NOTE)
                    var title = data.getStringExtra(Constant.TITLE)
                    var datatime=date.split("\\s".toRegex())
                    val note = Note(note_id, desc, datatime[0],datatime[1], title)
                    noteViewModel!!.insert(note)
                    Toast.makeText(
                        applicationContext,
                        R.string.saved,
                        Toast.LENGTH_LONG
                    ).show()
                }
                UPDATE_NOTE_ACTIVITY_REQUEST_CODE -> { // Code to update the note
                    var date = data.getStringExtra(Constant.DATE)
                    var note_id = data.getStringExtra(Constant.NOTE_ID)
                    var desc = data.getStringExtra(Constant.NOTE)
                    var title = data.getStringExtra(Constant.TITLE)
                    var datatime=date.split("\\s".toRegex())
                    val note = Note(note_id, desc, datatime[0],datatime[1], title)
                    when(data.getStringExtra(Constant.PURPOSE))
                    {
                        Constant.UPDATE->{
                            noteViewModel!!.update(note)
                        }Constant.DELETE->{
                           noteViewModel!!.delete(note)
                         }
                    }

                    Toast.makeText(
                        applicationContext,
                        R.string.updated,
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        applicationContext,
                        R.string.not_saved,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }else if(resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE ) {
                Toast.makeText(
                    applicationContext,
                    R.string.not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE ) {
                Toast.makeText(
                    applicationContext,
                    R.string.not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
            //Write your code if there's no result
        }
    }

    companion object {
         const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }

}