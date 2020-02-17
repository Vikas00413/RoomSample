package com.demo.test.noteapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.demo.test.noteapp.MyApplication
import com.demo.test.noteapp.database.NoteRoomDatabase
import javax.inject.Inject

/**
 * This base class for access AndroidViewModel and extend by NoteCrudOperationsViewModel,AllNoteViewModel
 */
abstract class BaseViewModel: AndroidViewModel {
    @Inject
    lateinit var database:NoteRoomDatabase

    constructor(application: Application):super(application){
        MyApplication.instance!!.getNetComponent().inject(this)

    }
}