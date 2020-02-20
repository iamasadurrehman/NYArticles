package com.a2l.nyarticles.common

/**
 * Package Name : com.a2l.nyarticles.common
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable? = null) {
  companion object {
    fun <T> success(data: T) = Resource(Success, data)

    fun error(throwable: Throwable) = Resource(Failure, null, throwable)

    fun <T> loading(data: T? = null) = Resource(Loading, data)
  }
}

sealed class Status
object Success: Status()
object Failure: Status()
object Loading: Status()