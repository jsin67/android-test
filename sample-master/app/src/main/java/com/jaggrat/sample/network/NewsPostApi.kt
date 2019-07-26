package com.jaggrat.sample.network

import com.jaggrat.sample.model.ParentObject
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface NewsPostApi {
    /**
     * Get the list of the news pots from the API
     */
    @GET("/309PryD")
    fun getNewsPost(): Observable<ParentObject>
}