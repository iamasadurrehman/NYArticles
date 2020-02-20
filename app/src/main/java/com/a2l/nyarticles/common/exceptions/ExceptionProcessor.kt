package com.a2l.nyarticles.common.exceptions

import android.app.Activity
import android.util.Log
import com.a2l.nyarticles.R
import com.a2l.nyarticles.common.data.ApiErrorResponse
import com.a2l.nyarticles.common.data.BadRequest
import com.a2l.nyarticles.common.showDialog
import com.a2l.nyarticles.common.showToast
import com.a2l.nyarticles.common.showToastException
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 * Package Name : com.a2l.nyarticles.common.exceptions
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

class ExceptionProcessor(val activity: Activity) : ExceptionHandler {

    fun process(t: Throwable) {
        when (t) {
            is HttpException -> handleHttpException(t)
            is IOException -> handleNoInternet(NetworkException())
            else -> handleGeneralException(GeneralException())
        }
    }

    private fun handleHttpException(exception: HttpException) {
        println(exception)
        when {
            exception.code() == 401 -> {
                val errorResponse = exception.response()?.errorBody()?.string()

                handleUnauthorizedException(
                    UnauthorizedException(),
                    errorResponse
                        ?: ""
                )
            }
            exception.code() == 400 -> handleUnauthorizedBadRequestException(exception.response()?.errorBody())
            else -> handleApiException(exception)
        }
    }

    override fun handleNoInternet(e: NetworkException) {
        showToast(activity, R.string.error_no_internet_connection)
    }

    override fun handleGeneralException(e: GeneralException) {
        showToast(activity)
    }

    override fun handleApiException(e: HttpException) {
        val string = e.response()?.errorBody()?.string()
        if (!string.isNullOrEmpty()) {
            try {
                val gson = Gson()
                val errorBody: ApiErrorResponse = gson.fromJson(string, ApiErrorResponse::class.java)

                val showDialog = showDialog(
                    context = activity,
                    message = errorBody.fault.faultstring
                )
                if (!activity.isFinishing) {
                    showDialog.show()
                }
            } catch (e: Exception) {
/*
                Log.e("ExceptionHandler", e.message, e)
*/
                showToastException(activity, "Api exp " + e.message)
            }
        }
    }

    override fun handleUnauthorizedException(e: UnauthorizedException, errorBody: String) {
        showToast(activity, errorBody)
        Log.d("UnauthorizedException", "401 Error")
    }

    private fun handleUnauthorizedBadRequestException(errorBody: ResponseBody?) {
        //showToast(activity, R.string.msg_bad_request)
        val gson = Gson()
        val error: BadRequest = gson.fromJson(errorBody?.string(), BadRequest::class.java)
        showToast(activity, error.errors.toString())
        Log.d("UnauthorizedException", "400 Error")
    }

}