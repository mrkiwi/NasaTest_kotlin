package com.nasa.test.util

import android.widget.ImageView
import com.nasa.test.R
import com.squareup.picasso.Picasso


fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

fun <T : Any> T?.notNullOrEmpty(f: (it: T) -> Unit) {
    if (this != null && this != "" && this != "null") f(this)
}

fun ImageView.setImageFromUrl(url: String) {
    Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(this)
}

fun androidx.appcompat.widget.AppCompatImageView.setImageFromUrl(url: String) {
    Picasso.get().load(url).into(this)
}