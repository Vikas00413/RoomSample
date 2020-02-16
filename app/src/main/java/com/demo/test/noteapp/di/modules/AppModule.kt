package com.demo.test.noteapp.di.modules


import com.demo.test.noteapp.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This is Base Application provider Module
 * use it for get application context
 */
@Module
class AppModule( var mApplication: MyApplication) {

    @Provides
    @Singleton
     fun providesApplication(): MyApplication {
        return mApplication
    }

}
