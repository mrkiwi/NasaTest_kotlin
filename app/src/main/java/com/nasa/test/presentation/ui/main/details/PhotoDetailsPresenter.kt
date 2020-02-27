package com.nasa.test.presentation.ui.main.details

import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.nasa.test.data.core.BaseApplication
import com.nasa.test.data.local.Constants.Arguments.PHOTO_ITEM
import com.nasa.test.data.model.common.PhotoItem
import com.nasa.test.presentation.base.BasePresenter
import com.nasa.test.util.notNull
import javax.inject.Inject

@InjectViewState
class PhotoDetailsPresenter @Inject constructor() : BasePresenter<PhotoDetailsView>() {

    init {
        BaseApplication.getAppComponent().inject(this)
    }

    fun checkBundle(arg: Bundle?) {
        arg.notNull { a ->
            if (a.containsKey(PHOTO_ITEM)) {
                viewState.setPhotoItem(a.getParcelable(PHOTO_ITEM) as PhotoItem)
            }
        }
    }

}