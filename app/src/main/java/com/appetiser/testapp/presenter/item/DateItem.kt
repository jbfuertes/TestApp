package com.appetiser.testapp.presenter.item

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.KotlinEpoxyHolder


@EpoxyModelClass(layout = R.layout.item_date)
abstract class DateItem : EpoxyModelWithHolder<DateItem.Holder>() {

    @EpoxyAttribute
    lateinit var date: String

    override fun bind(holder: Holder){
        holder.textDate.text = date
    }

    class Holder: KotlinEpoxyHolder() {

        val textDate by bind<TextView>(R.id.text_date)

    }
}