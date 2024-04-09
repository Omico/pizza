/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.annotation.SuppressLint
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch
import me.omico.pizza.databinding.FragmentMainBinding

class MainPage : BaseFragment<FragmentMainBinding>(
    contentLayoutId = R.layout.fragment_main,
    bindViewBinding = FragmentMainBinding::bind,
) {
    @SuppressLint("SetTextI18n")
    override fun FragmentMainBinding.setupView() {
        val pizzaAdapter = PizzaAdapter { position, pizza ->
            recyclerView.smoothScrollToPosition(position)
            viewModel.selectPizza(pizza)
        }
        recyclerView.adapter = pizzaAdapter
        recyclerView.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            .apply { carouselAlignment = CarouselLayoutManager.ALIGNMENT_CENTER }
            .apply {
                orientation = when (resources.configuration.orientation) {
                    ORIENTATION_LANDSCAPE -> CarouselLayoutManager.VERTICAL
                    else -> CarouselLayoutManager.HORIZONTAL
                }
            }
        PizzaFocusManager(recyclerView) { position ->
            viewModel.selectPizza(pizzaAdapter.currentList[position])
        }
        pizzaAdapter.submitList(pizzaList)

        pizzaSizeSelector.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            val selectedSize = when (checkedId) {
                R.id.small -> PizzaSize.SMALL
                R.id.medium -> PizzaSize.MEDIUM
                R.id.large -> PizzaSize.LARGE
                else -> error("Unknown size")
            }
            viewModel.selectPizzaSize(selectedSize)
        }

        with(addToppingsView.toppings) {
            pizzaToppingList.forEach { pizzaTopping ->
                addChip {
                    id = pizzaTopping.id
                    text = pizzaTopping.name
                    isCloseIconVisible = false
                    isCheckable = true
                }
            }
            setOnCheckedStateChangeListener { _, checkedIds ->
                pizzaToppingList.forEach { pizzaTopping ->
                    with(findViewById<Chip>(pizzaTopping.id)) {
                        isCloseIconVisible = isChecked
                    }
                }
                val selectedToppings = checkedIds
                    .mapNotNull { id -> pizzaToppingList.find { it.id == id } }
                viewModel.selectToppings(selectedToppings)
            }
        }

        checkout.setOnClickListener { navController.navigate(MainPageDirections.actionNavMainToNavCustomer()) }

        lifecycleScope.launch {
            viewModel.selectedPizza
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { pizza -> name.text = "${pizza.name}  $${pizza.price}" }
        }
    }

    private fun ChipGroup.addChip(block: Chip.() -> Unit) =
        Chip(requireContext()).apply(block).let(::addView)
}
