package com.beloushkin.dataclasssamples.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beloushkin.dataclasssamples.domain.FeedInteractor
import com.beloushkin.dataclasssamples.domain.FeedItem
import com.example.delegateadapter.delegate.diff.IComparableItem

class FeedPresentationModel(
    private val interactor: FeedInteractor = FeedInteractor(),
    private val viewModelFactory: FeedVMFactory = FeedVMFactory()
): ViewModel() {
    val modelLiveData by lazy {
        MutableLiveData<FeedViewModel>()
    }

    private var model: Model

    init {
        //usually need show loading state
        model = Model(interactor.getFeedItems())
        update()
    }

    fun onOfferClicked(offer: FeedItem.Offer) {
        // move to top
        update {
            copy(
                items = listOfNotNull(
                    items.firstOrNull { item ->
                        item is FeedItem.Offer && item.id == offer.id
                    }
                ) + items.filterNot{ item ->
                    item is FeedItem.Offer && item.id == offer.id
                }
            )
        }
    }

    fun onHideAdvertClicked(advert: FeedItem.Advert) {
        // remove advert from the List
        update {
            copy (
                items = items.filterNot { item ->
                    item is FeedItem.Advert && item.id == advert.id
                }
            )
        }
    }

    private fun update(mapper: Model.()-> Model = { this } ) {
        model = model.mapper()
        val viewModel = viewModelFactory.toViewModel(model)
        modelLiveData.value = viewModel
    }

    data class Model(
        val items: List<FeedItem>
    )
}


data class FeedViewModel(
    var items: List<IComparableItem>
)