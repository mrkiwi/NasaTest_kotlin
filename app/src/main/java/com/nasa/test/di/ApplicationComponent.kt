package com.nasa.test.di

import com.nasa.test.di.modules.ApplicationModule
import com.nasa.test.di.modules.NavigationModule
import com.nasa.test.di.modules.NetworkModule
import com.nasa.test.presentation.base.BaseActivity
import com.nasa.test.presentation.ui.main.MainPresenter
import com.nasa.test.presentation.ui.main.details.PhotoDetailsPresenter
import com.nasa.test.presentation.ui.main.list.PhotoListPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, NavigationModule::class])
interface ApplicationComponent {
    fun inject(activity: BaseActivity)
    fun inject(presenter: MainPresenter)
    fun inject(presenter: PhotoListPresenter)
    fun inject(presenter: PhotoDetailsPresenter)
}