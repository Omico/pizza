/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

// Didn't use MVI due to the complexity of the app.
class PizzaViewModel : ViewModel() {
    private val _selectedPizza = MutableStateFlow(pizzaList.first())
    val selectedPizza: StateFlow<Pizza> = _selectedPizza.asStateFlow()

    private val selectedPizzaSize = MutableStateFlow(PizzaSize.SMALL)

    private val selectedToppings: MutableStateFlow<List<PizzaTopping>> =
        MutableStateFlow(emptyList())

    private val costumerInformation: MutableStateFlow<CostumerInformation> =
        MutableStateFlow(CostumerInformation.Empty)

    val receiptInformation: Flow<ReceiptInformation> =
        combine(
            _selectedPizza,
            selectedPizzaSize,
            selectedToppings,
            costumerInformation,
        ) {
                pizza,
                pizzaSize,
                toppings,
                costumerInformation,
            ->
            ReceiptInformation(
                pizza = pizza,
                pizzaSize = pizzaSize,
                pizzaToppings = toppings,
                totalPrice = run {
                    pizza.price
                        .plus(pizzaSize.price)
                        .plus(toppings.sumOf(PizzaTopping::price))
                        .round(2)
                },
                costumerInformation = costumerInformation,
            )
        }

    val receiptInformationText: Flow<String> =
        combine(
            _selectedPizza,
            selectedPizzaSize,
            selectedToppings,
            costumerInformation,
        ) {
                pizza,
                pizzaSize,
                toppings,
                costumerInformation,
            ->
            buildString {
                append("${pizza.name} (${pizzaSize.displayName})")
                if (pizzaSize != PizzaSize.SMALL) append(" +$${pizzaSize.price}")
                appendLine()
                if (toppings.isNotEmpty()) {
                    appendLine("Toppings:")
                    toppings.forEach { topping -> appendLine("    ${topping.name} +$${topping.price}") }
                }
                appendLine()
                appendLine("Customer Information")
                appendLine("    Name: ${costumerInformation.name}")
                appendLine("    Phone: ${costumerInformation.phoneNumber}")
                appendLine("    Email: ${costumerInformation.email}")
                appendLine("    Address: ${costumerInformation.address}")
                appendLine()
                appendLine("Total: $${calculateTotalPrice(pizza, pizzaSize, toppings)}")
            }
        }

    fun selectPizza(pizza: Pizza) {
        viewModelScope.launch {
            _selectedPizza.emit(pizza)
        }
    }

    fun selectPizzaSize(pizzaSize: PizzaSize) {
        viewModelScope.launch {
            selectedPizzaSize.emit(pizzaSize)
        }
    }

    fun selectToppings(pizzaToppings: List<PizzaTopping>) {
        viewModelScope.launch {
            selectedToppings.emit(pizzaToppings)
        }
    }

    fun submitCostumerInformation(customInformation: CostumerInformation) {
        viewModelScope.launch {
            costumerInformation.emit(customInformation)
        }
    }

    private fun calculateTotalPrice(
        pizza: Pizza,
        pizzaSize: PizzaSize,
        toppings: List<PizzaTopping>,
    ): Double =
        pizza.price
            .plus(pizzaSize.price)
            .plus(toppings.sumOf(PizzaTopping::price))
            .round(2)
}
