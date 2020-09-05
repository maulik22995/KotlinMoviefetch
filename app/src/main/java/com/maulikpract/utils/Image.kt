package com.maulikpract.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.maulikpract.R

@BindingAdapter("loadUrl")
fun ImageView.loadImage(url : String?){
    Glide.with(this).asBitmap().load("${resources.getString(R.string.image_path)}$url").into(this)
}