package com.sayursehat.paterai.model

data class User(
    val uid: String? = null,
    val cart: Cart = Cart()
)

data class Cart(
    val totalPrice: Int = 0,
    val listProduct: List<Product> = listOf()
)

data class Product(
    val count: Int? = null,
    val id: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val price: Int? = null,
    val totalPrice: Int? = null,
    val weight: String? = null
)
