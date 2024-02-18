/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import androidx.navigation.ui.setupWithNavController
import me.omico.pizza.databinding.FragmentHelpBinding

class HelpPage : BaseFragment<FragmentHelpBinding>(
    contentLayoutId = R.layout.fragment_help,
    bindViewBinding = FragmentHelpBinding::bind,
) {
    override fun FragmentHelpBinding.setupView() {
        toolbar.setupWithNavController(navController)
    }
}
