package com.appetiser.testapp.presenter.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.KotlinEpoxyHolder
import com.appetiser.testapp.presenter.common.extension.loadImage


@EpoxyModelClass(layout = R.layout.item_track)
abstract class TrackItem : EpoxyModelWithHolder<TrackItem.Holder>() {

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    lateinit var price: String

    @EpoxyAttribute
    lateinit var genre: String

    @EpoxyAttribute
    lateinit var imageUrl: String

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder){
        holder.textName.text = name
        holder.textPrice.text = price
        holder.textGenre.text = genre
        holder.layoutTrack.setOnClickListener(clickListener)
        holder.loadImage(imageUrl)
    }

    class Holder: KotlinEpoxyHolder() {

        val textName by bind<TextView>(R.id.text_name)
        val textPrice by bind<TextView>(R.id.text_price)
        val textGenre by bind<TextView>(R.id.text_genre)
        val layoutTrack by bind<ConstraintLayout>(R.id.layout_track)
        private val imageArtwork by bind<ImageView>(R.id.image_artwork)

        internal fun loadImage(imageUrl: String){
            imageArtwork.loadImage(imageUrl = imageUrl)
        }

    }
}