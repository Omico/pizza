/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.annotation.SuppressLint
import androidx.navigation.ui.setupWithNavController
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
        }
    }
}
