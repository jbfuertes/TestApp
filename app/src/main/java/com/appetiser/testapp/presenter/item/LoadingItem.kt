package com.appetiser.testapp.presenter.item

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.KotlinEpoxyHolder


@EpoxyModelClass(layout = R.layout.item_loading)
abstract class LoadingItem : EpoxyModelWithHolder<LoadingItem.Holder>() {

    override fun bind(holder: Holder){
    }

    class Holder: KotlinEpoxyHolder() {

    }
}