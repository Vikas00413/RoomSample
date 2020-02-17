package com.demo.test.noteapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * This is abstract for Room database and access dao interface
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?


}