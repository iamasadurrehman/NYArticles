package com.a2l.nyarticles.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.a2l.nyarticles.base.BaseViewModel
import com.a2l.nyarticles.common.Resource
import com.a2l.nyarticles.models.MostPopularArticles
import com.a2l.nyarticles.repositories.ArticleRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Package Name : com.a2l.nyarticles.viewmodels
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

class ArticlesViewModel : BaseViewModel() {
    val data: MutableLiveData<Resource<MostPopularArticles>> = MutableLiveData()
    private val repository = ArticleRepository()

    fun getMostPopularArticles(section: String) {
        val disposable = repository.getMostPopularArticles(section)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnSubscribe { data.value = Resource.loading() }
            ?.subscribe(
                {
                    data.value = Resource.success(it)
                }, {
                    data.value = Resource.error(it)
                    Log.e("Error", it.message)
                }
            )

        if (disposable != null) disposables.add(disposable)
    }
}
