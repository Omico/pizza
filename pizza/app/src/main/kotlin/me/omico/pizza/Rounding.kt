/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}
