package com.a2l.nyarticles.common.data


import com.google.gson.annotations.SerializedName

/**
 * Package Name : com.a2l.nyarticles.common.data
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

data class ApiErrorResponse(
    @SerializedName("fault")
    val fault: Fault
)

data class Detail(
    @SerializedName("errorcode")
    val errorcode: String
)

data class Fault(
    @SerializedName("detail")
    val detail: Detail,
    @SerializedName("faultstring")
    val faultstring: String
)