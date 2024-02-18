/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

enum class PizzaSize(
    val displayName: String,
    val price: Double,
) {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 3.3),
    LARGE("Large", 4.4),
}
