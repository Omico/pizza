/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity

class PizzaActivity : FragmentActivity(R.layout.activity_pizza) {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
    }
}
