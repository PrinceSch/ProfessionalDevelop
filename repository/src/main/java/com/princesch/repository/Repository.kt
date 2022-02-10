package com.princesch.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}