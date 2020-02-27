package com.nasa.test.presentation.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.nasa.test.presentation.base.BaseFragment


interface Navigator {

    fun bind(fragmentManager: FragmentManager, @IdRes containerId: Int)

    fun unbind()

    fun switchFragment(_fragment: BaseFragment)

    fun switchFragment(_fragment: BaseFragment, _addToBackStack: Boolean)

    fun getCurrentFragment(): BaseFragment

    fun getCountFragmentsInBackStack(): Int

    fun handleBack(): Boolean

}