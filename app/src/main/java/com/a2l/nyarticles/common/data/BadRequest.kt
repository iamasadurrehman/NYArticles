package com.a2l.nyarticles.common.data


import com.google.gson.annotations.SerializedName

/**
 * Package Name : com.a2l.nyarticles.common.data
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

data class BadRequest(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("results")
    val results: List<Any>,
    @SerializedName("status")
    val status: String
)