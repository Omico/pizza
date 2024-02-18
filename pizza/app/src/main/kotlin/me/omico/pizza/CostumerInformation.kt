/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.pizza

data class CostumerInformation(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
) {
    companion object {
        val Empty: CostumerInformation = CostumerInformation()
    }
}
