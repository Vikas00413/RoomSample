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

class NoteViewModel: BaseViewModel {
    private val TAG = this.javaClass.simpleName
    private val noteDao: NoteDao?
    private val noteDB: NoteRoomDatabase?
    val allNotes: LiveData<List<Note>>?
    fun insert(note: Note?) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note?) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note?) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }

     abstract class OperationsAsyncTask  constructor(var mAsyncTaskDao: NoteDao?) : AsyncTask<Note?, Void?, Void?>() {
        protected override fun doInBackground(vararg params: Note?): Void? {
            return null
        }

    }

    private inner class InsertAsyncTask internal constructor(mNoteDao: NoteDao?) : OperationsAsyncTask(mNoteDao) {
        override fun doInBackground(vararg params: Note?): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }
    }

    private inner class UpdateAsyncTask internal constructor(noteDao: NoteDao?) : OperationsAsyncTask(noteDao) {
        override fun doInBackground(vararg params: Note?): Void? {
            mAsyncTaskDao!!.update(params[0])
            return null
        }
    }

    private inner class DeleteAsyncTask(noteDao: NoteDao?) : OperationsAsyncTask(noteDao) {
        override fun doInBackground(vararg params: Note?): Void? {
            mAsyncTaskDao!!.delete(params[0])
            return null
        }
    }
  constructor(application: Application?) : super(application!!){
      //noteDB = DatabaseClient.getInstance(application).appDatabase
      noteDB = database
      noteDao = noteDB!!.noteDao()
      allNotes = noteDao!!.allNotes
  }

}