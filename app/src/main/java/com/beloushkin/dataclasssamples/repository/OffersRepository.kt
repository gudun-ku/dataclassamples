package com.beloushkin.dataclasssamples.repository

import com.beloushkin.dataclasssamples.domain.FeedItem
import kotlin.random.Random

interface IOffersRepository {
    fun getItems(count: Int): List<FeedItem.Offer>
}

class OffersRepository: IOffersRepository {
    override fun getItems(count: Int): List<FeedItem.Offer> = (0..count).map { id ->
        when(Random.nextInt(3)) {
            0 -> AUDI
            1 -> BMW
            else -> LADA
        }.copy(id = "$id")
    }

    companion object {
        private val AUDI = FeedItem.Offer(
            id="",
            title = "Audi A5, 2016",
            text = "Машина в идеальном состоянии",
            price = 2_000_000
        )

        private val BMW = FeedItem.Offer(
            id="",
            title = "BMW X5, 2017",
            text = "Машина в отличном состоянии",
            price = 3_000_000
        )
        private val LADA = FeedItem.Offer(
            id="",
            title = "LADA 2109, 1997",
            text = "На ходу",
            price = 30_000
        )




    }
}