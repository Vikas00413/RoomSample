package com.demo.test.noteapp.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.demo.test.noteapp.database.Note
import com.demo.test.noteapp.database.NoteDao
import com.demo.test.noteapp.util.Coroutines

/**
 * This class is use perform all crud operations
 */
class NoteCrudOperationsViewModel: BaseViewModel {
    private val TAG = this.javaClass.simpleName
    private val noteDao: NoteDao?
    val allNotes: LiveData<List<Note>>?
    fun insert(note: Note?) {
        InsertTask(noteDao).doInBackground(note)
    }

    fun update(note: Note?) {
        UpdateTask(noteDao).doInBackground(note)
    }

    fun delete(note: Note?) {
        DeleteTask(noteDao).doInBackground(note)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }

     abstract class OperationsTask  constructor(var mAsyncTaskDao: NoteDao?) {

       abstract  fun doInBackground( params: Note?)

    }


    /**
     * This class is use for insert data in note table
     */
    private inner class InsertTask internal constructor(mNoteDao: NoteDao?) : OperationsTask(mNoteDao) {
         override fun doInBackground( note: Note?) {

             Coroutines.io {
                 mAsyncTaskDao!!.insert(note)
             }
        }
    }
    /**
     * This class is use for update data in note table
     */
    private inner class UpdateTask internal constructor(noteDao: NoteDao?) : OperationsTask(noteDao) {
        override fun doInBackground( note: Note?) {
             Coroutines.io {
                 mAsyncTaskDao!!.update(note)
             }
        }
    }

    /**
     * This class is use for delete data from note table
     */
    private inner class DeleteTask(noteDao: NoteDao?) : OperationsTask(noteDao) {
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