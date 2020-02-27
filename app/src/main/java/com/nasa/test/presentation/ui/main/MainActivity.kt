package com.nasa.test.presentation.ui.main

import com.arellomobile.mvp.presenter.InjectPresenter
import com.nasa.test.R
import com.nasa.test.presentation.base.BaseActivity
import com.nasa.test.presentation.ui.main.list.PhotoListFragment

class MainActivity : BaseActivity(), MainView {


    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun initUI() {
        navigator.switchFragment(PhotoListFragment.newInstance(), false)
    }

    override fun getContainerRes(): Int = R.id.content_frame

    override fun getLayoutRes(): Int = R.layout.activity_main

}