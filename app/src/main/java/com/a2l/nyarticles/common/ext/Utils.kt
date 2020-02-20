package com.a2l.nyarticles.common.ext

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.a2l.nyarticles.R
import com.google.android.material.snackbar.Snackbar

/**
 * Package Name : com.a2l.nyarticles.common.ext
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

fun showDialog(
    context: Context,
    title: String = "",
    message: String,
    buttonText: Int = R.string.lbl_ok,
    callback: DialogInterface.OnClickListener,
    negativeButtonText: Int = R.string.lbl_cancel,
    cancelCallback: DialogInterface.OnClickListener? = null,
    cancelable: Boolean = true
): AlertDialog {
    val builder = AlertDialog.Builder(context)

    builder.setTitle(title)
    builder.setMessage(message)

    builder.setPositiveButton(buttonText, callback)

    cancelCallback?.let {
        builder.setNegativeButton(negativeButtonText, cancelCallback)
    }

    builder.setCancelable(cancelable)

    return builder.create()
}

fun createProgressDialog(context: Context, message: Int = R.string.lbl_loading): ProgressDialog {
    val dialog = ProgressDialog(context)
    dialog.setMessage(context.getString(message))
    dialog.setCancelable(false)
    return dialog
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

fun showSnackBar(view: View, message: Int = R.string.error_general) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}
