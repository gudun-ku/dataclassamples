package com.beloushkin.dataclasssamples.presentation

import com.beloushkin.dataclasssamples.domain.FeedItem
import com.beloushkin.dataclasssamples.view.adapter.AdvertViewModel
import com.beloushkin.dataclasssamples.view.adapter.DividerViewModel
import com.beloushkin.dataclasssamples.view.adapter.OfferViewModel
import com.example.delegateadapter.delegate.diff.IComparableItem
import java.text.DecimalFormat

class FeedVMFactory {

    private val decimalFormat = DecimalFormat("###,###.##")

    fun toViewModel(model: FeedPresentationModel.Model): FeedViewModel {
        val items = model.items.map {item ->
            when (item) {
                is FeedItem.Offer -> item.toViewModel()
                is FeedItem.Advert -> item.toViewModel()
            }
        }
        var itemsWithDivider = items
            .map { item -> listOf(item, DividerViewModel)}
            .flatten()
        return FeedViewModel(itemsWithDivider)
    }

    private fun FeedItem.Offer.toViewModel(): OfferViewModel = OfferViewModel(
        title = title,
        text = text,
        price = decimalFormat.format(price) + " Р",
        payload = this
    )

    private fun FeedItem.Advert.toViewModel(): AdvertViewModel = AdvertViewModel(
        payload = this
    )
}