package com.example.recyclerview.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroModel(
    var name:String = "",
    var detail: String = "",
    var photo: Int = 0

):Parcelable