/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.view.View

data class PizzaTopping(
    val name: String,
    val id: Int = View.generateViewId(),
    val price: Double = 2.39,
)
