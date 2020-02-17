package com.beloushkin.dataclasssamples.view.adapter

import com.beloushkin.dataclasssamples.R
import com.beloushkin.dataclasssamples.domain.FeedItem
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_advert.*
import kotlinx.android.synthetic.main.item_advert.tv_title

class AdvertAdapter(
    private val onClick: (payload: FeedItem.Advert) -> Unit
): KDelegateAdapter<AdvertViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_advert

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is AdvertViewModel

    override fun onBind(item: AdvertViewModel, viewHolder: KViewHolder) = with(viewHolder){
        tv_title.text = item.content().toString()
        btn_hide.setOnClickListener { onClick(item.payload)}
    }
}

data class AdvertViewModel (
    val payload: FeedItem.Advert
): IComparableItem {
    override fun id(): Any = this::class.java
    override fun content(): Any = this
}