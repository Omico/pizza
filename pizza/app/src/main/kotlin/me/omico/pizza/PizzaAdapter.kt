/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.omico.pizza.databinding.ItemPizzaBinding

class PizzaAdapter(
    private val onPizzaClick: (position: Int, pizza: Pizza) -> Unit,
) : ListAdapter<Pizza, PizzaAdapter.ViewHolder>(PizzaDiffCallback) {
    inner class ViewHolder(
        private val binding: ItemPizzaBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int): Unit = with(binding) {
            val pizza = getItem(position)
            root.setOnClickListener { onPizzaClick(position, pizza) }
            imageView.setImageResource(pizza.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .let { ItemPizzaBinding.inflate(it, parent, false) }
            .let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = holder.bind(position)
}

private object PizzaDiffCallback : DiffUtil.ItemCallback<Pizza>() {
    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean = oldItem == newItem
}
