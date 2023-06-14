package com.sayursehat.paterai.model

data class Cart(
    val store: String,
    val location: String,
    val total: Int,
    val product: List<Vegetable>
)
