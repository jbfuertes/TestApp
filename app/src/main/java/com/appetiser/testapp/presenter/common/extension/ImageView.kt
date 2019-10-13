package com.appetiser.testapp.presenter.common.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.appetiser.testapp.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(
    imageUrl: String?,
    placeHolderDrawable: Drawable = ContextCompat.getDrawable(
        this.context,
        R.drawable.ic_place_holder
    )!!
) {
    if (imageUrl.isNullOrEmpty()) {
        setImageDrawable(placeHolderDrawable)
        return
    }

    Picasso.get()
        .load(imageUrl)
        .placeholder(placeHolderDrawable)
        .error(placeHolderDrawable)
        .into(this)
}