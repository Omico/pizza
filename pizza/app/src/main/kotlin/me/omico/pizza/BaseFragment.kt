/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

// Only for this shitty assignment.
// If someone except me is reading this, please don't follow this pattern.
// Creating and using a base fragment in all fragments is a bad practice.
// Inheritance is not suitable for this case.
// Who the fuck is going to use this pattern in a real project?
// If so, I definitely kick his/her ass off!
abstract class BaseFragment<Binding : ViewBinding>(
    @LayoutRes contentLayoutId: Int,
    private val bindViewBinding: (View) -> Binding,
) : Fragment(contentLayoutId) {
    protected open fun Binding.setupView(): Unit = Unit

    protected val viewModel: PizzaViewModel by activityViewModels()

    protected val navController: NavController by lazy(
        mode = LazyThreadSafetyMode.NONE,
        initializer = ::findNavController,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewBinding(view).setupView()
    }
}
