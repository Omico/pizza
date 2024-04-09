/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.launch
import me.omico.pizza.databinding.FragmentReceiptBinding

class ReceiptPage : BaseFragment<FragmentReceiptBinding>(
    contentLayoutId = R.layout.fragment_receipt,
    bindViewBinding = FragmentReceiptBinding::bind,
) {
    @SuppressLint("SetTextI18n")
    override fun FragmentReceiptBinding.setupView() {
        toolbar.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.receiptInformationText.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { text -> receiptInformationView.content.text = text }
        }
    }
}
