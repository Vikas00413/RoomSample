package com.demo.test.noteapp

import android.app.Application
import com.demo.test.noteapp.di.components.DaggerNetComponent.builder
import com.demo.test.noteapp.di.components.NetComponent
import com.demo.test.noteapp.di.modules.AppModule
import com.demo.test.noteapp.di.modules.NetModule


/**
 * This is Base Application class used in AndroidManifest.xml in application tag
 * @author vikas kesharvani
 */
class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        if (instance == null) {
            instance = this
        }
        mNetComponent = builder()
            .appModule(AppModule(this))
            .netModule(NetModule())

            .build()



    }

    companion object {
        @JvmStatic  lateinit var mNetComponent: NetComponent
        public var instance: MyApplication? = null
             set


    }

    fun getNetComponent(): NetComponent {
        return mNetComponent!!
    }


}
