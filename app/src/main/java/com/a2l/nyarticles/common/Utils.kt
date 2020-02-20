package com.a2l.nyarticles.common

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.a2l.nyarticles.R

/**
 * Package Name : com.a2l.nyarticles.common
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

fun showDialog(
    context: Context,
    title: String = "",
    message: String,
    buttonText: Int = R.string.lbl_ok
): AlertDialog {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(buttonText) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}

fun showToast(
    context: Context,
    message: Int = R.string.error_general,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, message, duration).show()
}

fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun showToastException(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}