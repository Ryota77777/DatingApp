package com.example.talkerbanda.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("custom:src")
    @JvmStatic
    fun setImageResource(imageView: ImageView, resourceId: Int?) {
        resourceId?.let {
            imageView.setImageResource(it)
        }
    }
}

