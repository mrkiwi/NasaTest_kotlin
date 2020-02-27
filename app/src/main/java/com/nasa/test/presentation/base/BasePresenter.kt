package com.nasa.test.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    private val mCompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    fun addDisposableFromView(d: Disposable) {
        addDisposable(d)
    }

}