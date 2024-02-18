/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

data class ReceiptInformation(
    val pizza: Pizza,
    val pizzaSize: PizzaSize,
    val pizzaToppings: List<PizzaTopping>,
    val totalPrice: Double,
    val costumerInformation: CostumerInformation,
)
