/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.flow.first
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
            navController.navigate(CustomerPageDirections.actionNavCustomerToNavReceipt())

            // Save the receipt as a txt file (“receipt.txt”) in the internal storage.
            lifecycleScope.launch {
                requireContext().filesDir.resolve("receipt.txt")
                    .writeText(viewModel.receiptInformationText.first())
            }
        }
    }
}
