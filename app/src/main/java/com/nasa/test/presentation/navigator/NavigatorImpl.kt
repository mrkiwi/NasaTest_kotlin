package com.nasa.test.presentation.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.nasa.test.presentation.base.BaseFragment
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {

    private var fragmentManager: FragmentManager? = null
    private var containerId: Int = 0

    override fun bind(fragmentManager: FragmentManager, @IdRes containerId: Int) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
    }

    override fun unbind() {
        fragmentManager = null
    }

    override fun switchFragment(_fragment: BaseFragment) {
        switchFragment(_fragment, true)
    }

    override fun switchFragment(_fragment: BaseFragment, _addToBackStack: Boolean) {
        if (containerId == 0) {
            throw IllegalArgumentException("Init fragment container before use navigator")
        }
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(containerId, _fragment, _fragment.javaClass.name)

        if (_addToBackStack) {
            fragmentTransaction?.addToBackStack(_fragment.javaClass.name)
        }
        fragmentTransaction?.commit()
    }

    override fun getCurrentFragment(): BaseFragment {
        return fragmentManager?.findFragmentById(containerId) as BaseFragment
    }

    override fun getCountFragmentsInBackStack(): Int {
        return fragmentManager!!.backStackEntryCount
    }

    override fun handleBack(): Boolean {
        return if (fragmentManager!!.backStackEntryCount > 0) {
            fragmentManager?.popBackStack()
            true
        } else {
            false
        }
    }
}