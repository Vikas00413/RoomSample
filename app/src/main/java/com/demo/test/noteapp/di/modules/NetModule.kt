package com.demo.test.noteapp.di.modules


import com.demo.test.noteapp.MyApplication
import com.demo.test.noteapp.database.DatabaseClient
import com.demo.test.noteapp.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class works internally by dagger for provide base module
 * Eg. Databse
 * @author vikas Kesharvani
 */
@Module
class NetModule {
    private val TAG = "NetModule"


    /**
     * This method works internally by dagger for providing Data Base
     * @param databaseClient is provided by provideDataBaseClient method
     * @param okHttpClient s provided by provideOkHttpClient method
     */
    @Provides
    @Singleton
    fun provideAppDataBase(databaseClient: DatabaseClient): NoteRoomDatabase {
        return databaseClient.appDatabase
    }

    /**
     * This method provide database clint
     */

    @Provides
    @Singleton
    fun provideDataBaseClient(application: MyApplication): DatabaseClient {
        return DatabaseClient.getInstance(application)
    }







}
