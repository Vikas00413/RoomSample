package com.demo.test.noteapp.database

import android.arch.persistence.room.Room
import android.content.Context

/**
 * This class is use for initialise Room Database config
 */
class DatabaseClient private constructor(  mCtx: Context) {
    private val DB_NAME = "note.db"

    //our app database object
    val appDatabase: NoteRoomDatabase

    init {

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, NoteRoomDatabase::class.java, DB_NAME).allowMainThreadQueries().build()
    }

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance as DatabaseClient
        }
    }
}