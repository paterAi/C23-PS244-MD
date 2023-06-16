package com.sayursehat.paterai.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vegetable(
    val id: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val price: Int? = null,
    val weight: String? = null,
    val description: String? = null
) : Parcelable
