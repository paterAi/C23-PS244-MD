package com.sayursehat.paterai.utils

import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {
        fun convertToIDRFormat(money: Number): String {
            val format = NumberFormat.getCurrencyInstance(
                Locale(
                    "id",
                    "ID"
                )
            )
            format.maximumFractionDigits = 0

            return format.format(money)

        }

        fun convertPriceWithRange(priceLowest: Number, priceHighest: Number): String {
            val range = "${convertToIDRFormat(priceLowest)} - ${convertToIDRFormat(priceHighest)}"
            return range

        }
    }
}