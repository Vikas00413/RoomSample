package com.demo.test.noteapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import com.demo.test.noteapp.database.DatabaseClient
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.database.NoteDao
import com.demo.test.noteapp.database.NoteRoomDatabase
import com.demo.test.noteapp.util.Coroutines

class NoteViewModel: BaseViewModel {
    private val TAG = this.javaClass.simpleName
    private val noteDao: NoteDao?
    val allNotes: LiveData<List<Note>>?
    fun insert(note: Note?) {
        InsertAsyncTask(noteDao).doInBackground(note)
    }

    fun update(note: Note?) {
        UpdateAsyncTask(noteDao).doInBackground(note)
    }

    fun delete(note: Note?) {
        DeleteAsyncTask(noteDao).doInBackground(note)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }

     abstract class OperationsAsyncTask  constructor(var mAsyncTaskDao: NoteDao?) {

       abstract  fun doInBackground( params: Note?)

    }



    private inner class InsertAsyncTask internal constructor(mNoteDao: NoteDao?) : OperationsAsyncTask(mNoteDao) {
         override fun doInBackground( note: Note?) {

             Coroutines.io {
                 mAsyncTaskDao!!.insert(note)
             }
        }
    }

    private inner class UpdateAsyncTask internal constructor(noteDao: NoteDao?) : OperationsAsyncTask(noteDao) {
        override fun doInBackground( note: Note?) {
             Coroutines.io {
                 mAsyncTaskDao!!.update(note)
             }
        }
    }

    private inner class DeleteAsyncTask(noteDao: NoteDao?) : OperationsAsyncTask(noteDao) {
        override fun doInBackground( note: Note?) {
             Coroutines.io {
                 mAsyncTaskDao!!.delete(note)
             }
        }
    }
  constructor(application: Application?) : super(application!!){
      noteDao = database!!.noteDao()
      allNotes = noteDao!!.allNotes
  }



}