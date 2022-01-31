package com.princesch.profdevelop.view.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.viewmodel.Interactor
import com.princesch.profdevelop.utils.isOnline
import com.princesch.profdevelop.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            //TODO
        }
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun renderData(dataModel: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}