package com.nasa.test.presentation.ui.main.list

import com.arellomobile.mvp.InjectViewState
import com.nasa.test.data.core.BaseApplication
import com.nasa.test.data.network.CommonService
import com.nasa.test.presentation.base.BasePresenter
import com.nasa.test.util.notNull
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject


@InjectViewState
class PhotoListPresenter @Inject constructor() : BasePresenter<PhotoListView>() {

    @Inject
    lateinit var commonService: CommonService

    init {
        BaseApplication.getAppComponent().inject(this)
    }

    fun loadData() {
        addDisposable(commonService.getMarsRoversPhotos("curiosity", 1000, "fhaz")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.notNull { r ->
                    r.photos?.let { viewState.addItems(it) }
                }
            }, { e ->
                Timber.e(e)
                viewState.showMessage(e.localizedMessage)
            }))

    }

}