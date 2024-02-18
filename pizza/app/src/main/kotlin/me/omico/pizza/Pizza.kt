/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import androidx.annotation.DrawableRes

data class Pizza(
    val name: String,
    @DrawableRes val imageRes: Int,
    val price: Double = 17.49,
)
