package com.nasa.test.presentation.ui.main.list

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nasa.test.R
import com.nasa.test.data.model.common.PhotoItem
import com.nasa.test.presentation.base.BaseFragment
import com.nasa.test.presentation.ui.main.adapter.RoverPhotoAdapter
import com.nasa.test.presentation.ui.main.details.PhotoDetailsFragment
import com.nasa.test.util.view.ProgressHolder
import kotlinx.android.synthetic.main.fragment_list.*

class PhotoListFragment : BaseFragment(), PhotoListView {

    companion object {
        fun newInstance(): PhotoListFragment {
            val fragment = PhotoListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mPresenter: PhotoListPresenter

    private var mAdapter: RoverPhotoAdapter? = null

    override fun getLayoutResource(): Int = R.layout.fragment_list

    override fun initUI() {
        progressHolder = ProgressHolder(rv_photos)
        initAdapter()
        showProgress()
        mPresenter.loadData()
    }

    private fun initAdapter() {
        rv_photos.layoutManager = LinearLayoutManager(context)
        mAdapter = RoverPhotoAdapter(R.layout.item_rover_photo, ArrayList())
        rv_photos.adapter = mAdapter
        mAdapter?.setEmptyView(R.layout.holder_empty, rv_photos.parent as ViewGroup)
        mAdapter?.setOnItemClickListener { adapter, _, position ->
            openDetailsScreen(adapter.getItem(position) as PhotoItem)
        }
    }

    override fun addItems(list: List<PhotoItem>) {
        mAdapter?.setNewData(list)
        hideProgress()
    }

    override fun openDetailsScreen(item: PhotoItem) {
        getNavigator()?.switchFragment(PhotoDetailsFragment.newInstance(item))
    }


}