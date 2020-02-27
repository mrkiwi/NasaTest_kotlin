package com.nasa.test.presentation.ui.main.details

import com.arellomobile.mvp.MvpView
import com.nasa.test.data.model.common.PhotoItem
import com.nasa.test.presentation.base.BaseView

interface PhotoDetailsView : BaseView, MvpView {
    fun setPhotoItem(item: PhotoItem)
}