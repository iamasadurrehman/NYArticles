package com.a2l.nyarticles.common.exceptions

import retrofit2.HttpException

/**
 * Package Name : com.a2l.nyarticles.common.exceptions
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

interface ExceptionHandler {

    fun handleNoInternet(e: NetworkException)

    fun handleGeneralException(e: GeneralException)

    fun handleApiException(e: HttpException)

    fun handleUnauthorizedException(e: UnauthorizedException, errorBody: String = "")
}