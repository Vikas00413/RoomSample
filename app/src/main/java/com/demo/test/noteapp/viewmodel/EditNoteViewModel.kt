package com.demo.test.noteapp.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData

import android.util.Log
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.database.NoteDao
import com.demo.test.noteapp.database.NoteRoomDatabase

class EditNoteViewModel : BaseViewModel {
    private val TAG = this.javaClass.simpleName
    private val noteDao: NoteDao?
    fun getNote(noteId: String?): LiveData<Note>? {
        return noteDao!!.getNote(noteId)
    }

    constructor(application: Application) :super(application){
        Log.i(TAG, "Edit ViewModel")
        noteDao = database!!.noteDao()
    }


}