/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselSnapHelper

class PizzaFocusManager(
    recyclerView: RecyclerView,
    private val onPizzaChanged: (index: Int) -> Unit,
) : RecyclerView.OnScrollListener() {
    private val snapHelper = CarouselSnapHelper()

    init {
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) =
        snapHelper.findTargetSnapPosition(recyclerView.layoutManager!!, dx, dy).let(onPizzaChanged)
}
