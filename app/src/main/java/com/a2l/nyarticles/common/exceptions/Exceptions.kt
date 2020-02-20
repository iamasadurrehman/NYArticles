package com.a2l.nyarticles.common.exceptions

/**
 * Package Name : com.a2l.nyarticles.common.exceptions
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

sealed class CException constructor(msg: String? = null) : Exception(msg)

class GeneralException constructor(msg: String? = null) : CException(msg)

class NetworkException constructor(msg: String? = null) : CException(msg)

class UnauthorizedException constructor(msg: String? = null) : CException(msg)