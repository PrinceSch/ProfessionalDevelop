package com.princesch.profdevelop

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}