package com.a2l.nyarticles.repositories

import com.a2l.nyarticles.common.Constants.Companion.NY_TIMES_API_KEY
import com.a2l.nyarticles.models.MostPopularArticles
import com.a2l.nyarticles.network.RestClient
import io.reactivex.Observable

/**
 * Package Name : com.a2l.nyarticles.repositories
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

class ArticleRepository {
    private val restClient = RestClient()
    private val apiKey = NY_TIMES_API_KEY

    fun getMostPopularArticles(section: String): Observable<MostPopularArticles>? {
        return restClient.getApi()?.mostPopularArticles(section, apiKey)?.map {
            it
        }
    }
}
