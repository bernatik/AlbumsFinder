package com.alexbernat.albumsfinder.presentation

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(view: ImageView, url: String)
}