package com.beloushkin.dataclasssamples.domain

import com.beloushkin.dataclasssamples.repository.AdvertsRepository
import com.beloushkin.dataclasssamples.repository.IAdvertsRepository
import com.beloushkin.dataclasssamples.repository.IOffersRepository
import com.beloushkin.dataclasssamples.repository.OffersRepository

class FeedInteractor(
    private val offersRepo: IOffersRepository = OffersRepository(),
    private val advertsRepo: IAdvertsRepository = AdvertsRepository()
) {
    fun getFeedItems(): List<FeedItem> = offersRepo.getItems(100)
        .mapIndexed { idx, item ->
            when {
                idx % 4 == 3 -> listOf(item) + advertsRepo.getItems(1)
                else -> listOf(item)
            }
        }
        .flatten()
}