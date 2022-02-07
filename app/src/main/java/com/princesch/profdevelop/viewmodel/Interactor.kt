package com.princesch.profdevelop.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}