package com.beloushkin.dataclasssamples.view.adapter

import com.beloushkin.dataclasssamples.R
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

object DividerAdapter: KDelegateAdapter<DividerViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_divider

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] === DividerViewModel

    override fun onBind(item: DividerViewModel, viewHolder: KViewHolder) {
        // do nothing
    }
}


object DividerViewModel: IComparableItem {
    override fun id(): Any = this // object
    override fun content(): Any = this
}
