package com.nasa.test.data.core

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.nasa.test.di.ApplicationComponent
import com.nasa.test.di.DaggerApplicationComponent
import com.nasa.test.di.modules.ApplicationModule
import com.nasa.test.di.modules.NetworkModule
import timber.log.Timber

open class BaseApplication : MultiDexApplication() {

    companion object {
        var mComponent: ApplicationComponent? = null
        fun getAppComponent(): ApplicationComponent {
            return mComponent!!
        }

        operator fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        mComponent = buildComponent()
    }


    open fun getComponent(): ApplicationComponent? {
        return mComponent
    }

    private fun buildComponent(): ApplicationComponent? {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}