package com.nasa.test.di.modules

import com.nasa.test.presentation.navigator.Navigator
import com.nasa.test.presentation.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {
    @Binds
    internal abstract fun provideNavigator(nav: NavigatorImpl): Navigator
}