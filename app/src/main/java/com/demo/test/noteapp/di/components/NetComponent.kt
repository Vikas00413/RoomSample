package com.demo.test.noteapp.di.components





import com.demo.test.noteapp.database.DatabaseClient
import com.demo.test.noteapp.database.NoteRoomDatabase
import com.demo.test.noteapp.di.modules.AppModule
import com.demo.test.noteapp.di.modules.NetModule


import com.demo.test.noteapp.view.activity.MainActivity
import com.demo.test.noteapp.viewmodel.BaseViewModel
import dagger.Component

import javax.inject.Singleton

/**
 * This Is NetComponent ,it use provide Database client
 */
@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface NetComponent {
   // fun retrofit(): Retrofit

     fun databseClient():DatabaseClient
    fun dataBase():NoteRoomDatabase

    fun inject(baseActivity: MainActivity)
    fun inject(baseActivity: BaseViewModel)

    //fun application(): MyApplication


}