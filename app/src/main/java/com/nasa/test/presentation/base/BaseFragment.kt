package com.nasa.test.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nasa.test.presentation.navigator.Navigator
import com.nasa.test.util.notNull
import com.nasa.test.util.view.ProgressHolder


abstract class BaseFragment : MvpAppCompatFragment() {

    private var baseActivity: BaseActivity? = null

    protected var progressHolder: ProgressHolder? = null

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            baseActivity = context as BaseActivity?
        } catch (e: ClassCastException) {
            throw IllegalArgumentException("BaseFragment should be in BaseActivity")
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                baseActivity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUtils()
        initUI()
    }

    fun showMessage(message: String) {
        baseActivity?.showMessage(message)
    }

    fun initUtils() {

    }

    protected fun getNavigator(): Navigator? {
        return baseActivity?.navigator
    }

    abstract fun initUI()

    fun showProgress() {
        progressHolder.notNull { holder ->
            holder.show()
        }
    }

    fun hideProgress() {
        progressHolder.notNull { holder ->
            holder.hide()
        }
    }

}