/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.launch
import me.omico.pizza.databinding.FragmentCustomerBinding

class CustomerPage : BaseFragment<FragmentCustomerBinding>(
    contentLayoutId = R.layout.fragment_customer,
    bindViewBinding = FragmentCustomerBinding::bind,
) {
    @SuppressLint("SetTextI18n")
    override fun FragmentCustomerBinding.setupView() {
        toolbar.setupWithNavController(navController)

        submit.setOnClickListener {
            val costumerInformation = CostumerInformation(
                name = customerInformationView.customerNameText.text.toString(),
                phoneNumber = customerInformationView.phoneNumberText.text.toString(),
                email = customerInformationView.emailText.text.toString(),
                address = customerInformationView.addressText.text.toString(),
            )
            viewModel.submitCostumerInformation(costumerInformation)
            customerInformationView.root.visibility = View.GONE
            submit.visibility = View.GONE
            receiptInformationView.root.visibility = View.VISIBLE
        }

        lifecycleScope.launch {
            viewModel.receiptInformation
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { receiptInformation ->
                    val pizza = receiptInformation.pizza
                    val pizzaSize = receiptInformation.pizzaSize
                    val toppings = receiptInformation.pizzaToppings
                    val costumerInformation = receiptInformation.costumerInformation
                    receiptInformationView.content.text = buildString {
                        append("${pizza.name} (${pizzaSize.displayName})")
                        if (pizzaSize != PizzaSize.SMALL) append(" +$${pizzaSize.price}")
                        appendLine()
                        appendLine()
                        if (toppings.isNotEmpty()) {
                            appendLine("Toppings:")
                            toppings.forEach { topping -> appendLine("    ${topping.name} +$${topping.price}") }
                        }
                        appendLine("Customer Information")
                        appendLine("    Name: ${costumerInformation.name}")
                        appendLine("    Phone: ${costumerInformation.phoneNumber}")
                        appendLine("    Email: ${costumerInformation.email}")
                        appendLine("    Address: ${costumerInformation.address}")
                    }
                    receiptInformationView.totalPrice.text =
                        "Total: $${receiptInformation.totalPrice}"
                }
        }
    }
}
