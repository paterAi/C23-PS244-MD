package com.sayursehat.paterai.model

data class Store(
    val name: String,
    val lat: Double,
    val lon: Double,
    val product: List<Vegetable>
)
