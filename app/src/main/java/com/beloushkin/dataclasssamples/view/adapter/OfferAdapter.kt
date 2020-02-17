package com.beloushkin.dataclasssamples.view.adapter

import com.beloushkin.dataclasssamples.R
import com.beloushkin.dataclasssamples.domain.FeedItem
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_offer.*

class OfferAdapter(
    private val onClick: (payload: FeedItem.Offer) -> Unit
): KDelegateAdapter<OfferViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_offer

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is OfferViewModel

    override fun onBind(item: OfferViewModel, viewHolder: KViewHolder) = with(viewHolder){
        tv_title.text = item.title
        tv_text.text = item.text
        tv_price.text = item.price
        itemView.setOnClickListener { onClick(item.payload)}
    }
}


data class OfferViewModel (
    val title: String,
    val price: String,
    val text: String,
    val payload: FeedItem.Offer
): IComparableItem {
    override fun id(): Any = this::class.java
    override fun content(): Any = this
}