package com.sayursehat.paterai.model

data class Product(
    val name: String,
    val image: Int,
    val priceLowest: Int,
    val priceHighest: Int,
    val store: String
)
