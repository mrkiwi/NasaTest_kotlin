package com.nasa.test.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }

}