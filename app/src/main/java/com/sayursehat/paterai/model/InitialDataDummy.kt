package com.sayursehat.paterai.model

import com.sayursehat.paterai.R

object InitialDataDummy {
    fun getStores(): List<Store> {
        return listOf(
            Store("Go Mart", -2.980216, 104.733649, getVegetables()),
            Store("Toko Asep", -2.984635, 104.714313, getVegetables()),
            Store("My Mart", -2.997882, 104.720044, getVegetables()),
            Store("Toko Kita", -3.002182, 104.739182, getVegetables())
        )
    }

    fun getVegetables(): List<Vegetable> {
        return listOf(
            Vegetable("Wortel", R.drawable.dummy_wortel, 19550, "1 kg"),
            Vegetable("Brokoli", R.drawable.dummy_brokoli, 24000, "500 g"),
            Vegetable("Kangkung", R.drawable.dummy_kangkung, 4000, "1 Pcs"),
            Vegetable("Kentang", R.drawable.dummy_kentang, 20500, "1 kg")
        )
    }
}