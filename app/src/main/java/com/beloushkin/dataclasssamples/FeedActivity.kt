package com.beloushkin.dataclasssamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beloushkin.dataclasssamples.presentation.FeedPresentationModel
import com.beloushkin.dataclasssamples.presentation.FeedViewModel
import com.beloushkin.dataclasssamples.view.adapter.AdvertAdapter
import com.beloushkin.dataclasssamples.view.adapter.DividerAdapter
import com.beloushkin.dataclasssamples.view.adapter.OfferAdapter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private lateinit var presentation: FeedPresentationModel

    private val adapter: DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()
        bindRecycler()
    }


    private fun initPresentationModel() {
        presentation = ViewModelProviders.of(this)[FeedPresentationModel::class.java]
        presentation.modelLiveData.observe(this, Observer { model: FeedViewModel ->
            update(model)
        })
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun update(model: FeedViewModel) {
        adapter.swapData(model.items)
    }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(OfferAdapter(presentation::onOfferClicked))
        .add(AdvertAdapter (presentation::onHideAdvertClicked))
        .add(DividerAdapter)
        .build()


}
