package com.princesch.profdevelop

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}