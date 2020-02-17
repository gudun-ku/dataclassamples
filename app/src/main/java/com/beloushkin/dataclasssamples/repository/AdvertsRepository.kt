package com.beloushkin.dataclasssamples.repository

import com.beloushkin.dataclasssamples.domain.FeedItem
import kotlin.random.Random

interface IAdvertsRepository {
    fun getItems(count: Int): List<FeedItem.Advert>
}

class AdvertsRepository: IAdvertsRepository {

    private var id = 0

    override fun getItems(count: Int): List<FeedItem.Advert> = (0 until count ).map {
        FeedItem.Advert(
            id = "${id++}"
        )
    }
}