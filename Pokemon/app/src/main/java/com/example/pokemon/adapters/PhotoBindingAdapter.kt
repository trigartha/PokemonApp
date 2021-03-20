package com.example.pokemon.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Binding adapter for Glide to load an image in an ImageView by url
 */
@BindingAdapter("imageUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .fitCenter()
            .into(view)
    }
}