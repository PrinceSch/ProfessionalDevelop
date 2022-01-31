package com.princesch.profdevelop.model.datasource

import com.princesch.profdevelop.model.data.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}