package com.appetiser.testapp.presenter.item

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.KotlinEpoxyHolder


@EpoxyModelClass(layout = R.layout.item_error)
abstract class ErrorItem : EpoxyModelWithHolder<ErrorItem.Holder>() {

    @EpoxyAttribute
    lateinit var error: String

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder){
        holder.textError.text = error
        holder.layoutError.setOnClickListener(clickListener)
    }

    class Holder: KotlinEpoxyHolder() {

        val textError by bind<TextView>(R.id.text_error)
        val layoutError by bind<ConstraintLayout>(R.id.layout_error)

    }
}