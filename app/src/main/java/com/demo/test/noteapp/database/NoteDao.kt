package com.demo.test.noteapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * This is dao interface for accessing db operation methods
 */
@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note?)

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>?

    @Query("SELECT * FROM notes WHERE id=:noteId")
    fun getNote(noteId: String?): LiveData<Note>?

    @Update
    fun update(note: Note?)

    @Delete
    fun delete(note: Note?): Int
}