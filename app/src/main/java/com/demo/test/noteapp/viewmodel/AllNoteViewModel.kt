package com.demo.test.noteapp.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData

import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.database.NoteDao

/**
 * This is class is use for get all notes
 */
class AllNoteViewModel : BaseViewModel {
    private val noteDao: NoteDao?
    fun getNote(noteId: String?): LiveData<Note>? {
        return noteDao!!.getNote(noteId)
    }

    constructor(application: Application) :super(application){
        noteDao = database!!.noteDao()
    }


}