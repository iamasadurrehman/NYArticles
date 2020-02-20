package com.a2l.nyarticles.network

import com.a2l.nyarticles.models.MostPopularArticles
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Package Name : com.a2l.nyarticles.network
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

interface AppApi {

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/{section}")
    fun mostPopularArticles(@Path("section") section: String, @Query("api-key") apiKey: String): Observable<MostPopularArticles>

}