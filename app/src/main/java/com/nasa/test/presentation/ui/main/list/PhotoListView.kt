package com.nasa.test.presentation.ui.main.list

import com.arellomobile.mvp.MvpView
import com.nasa.test.data.model.common.PhotoItem
import com.nasa.test.presentation.base.BaseView

interface PhotoListView : BaseView, MvpView {
    fun addItems(list: List<PhotoItem>)
    fun openDetailsScreen(item: PhotoItem)
}