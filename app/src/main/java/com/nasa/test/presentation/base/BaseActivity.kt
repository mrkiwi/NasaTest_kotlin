package com.nasa.test.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nasa.test.data.core.BaseApplication
import com.nasa.test.presentation.navigator.Navigator
import com.nasa.test.util.notNull
import javax.inject.Inject


abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    @Inject
    lateinit var navigator: Navigator

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @IdRes
    protected open fun getContainerRes(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication[applicationContext!!].getComponent().notNull { c ->
            c.inject(this)
        }
        setContentView(getLayoutRes())

        initUtils()
        initUI()
    }

    @CallSuper
    protected fun initUtils() {
        if (getContainerRes() != 0) {
            navigator.bind(supportFragmentManager, getContainerRes())
        }
    }

    protected abstract fun initUI()

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}