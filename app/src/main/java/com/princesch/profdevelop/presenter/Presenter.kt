package com.princesch.profdevelop.presenter

import com.princesch.profdevelop.view.base.View
import com.princesch.profdevelop.model.data.AppState

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}