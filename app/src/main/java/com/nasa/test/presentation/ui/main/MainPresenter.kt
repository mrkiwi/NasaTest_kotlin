package com.nasa.test.presentation.ui.main

import com.arellomobile.mvp.InjectViewState
import com.nasa.test.data.core.BaseApplication
import com.nasa.test.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<MainView>() {

    init {
        BaseApplication.getAppComponent().inject(this)
    }


}