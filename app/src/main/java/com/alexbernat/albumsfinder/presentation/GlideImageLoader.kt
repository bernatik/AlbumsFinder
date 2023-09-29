package com.alexbernat.albumsfinder.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: ImageLoader {

    override fun loadImage(view: ImageView, url: String) {
        Glide.with(view).load(url).into(view)
    }
}