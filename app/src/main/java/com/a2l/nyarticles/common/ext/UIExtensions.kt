package com.a2l.nyarticles.common.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.a2l.nyarticles.R
import com.bumptech.glide.Glide

/**
 * Package Name : com.a2l.nyarticles.common.ext
 * Created by Asad Ur Rehman on 2/20/2020
 * Copyright (c) 2020 Asad. All rights reserved.
 */

fun de.hdodenhof.circleimageview.CircleImageView.loadImage(imageUri: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(imageUri)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.round_view)
        .override(200, 300)
        .into(this)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}