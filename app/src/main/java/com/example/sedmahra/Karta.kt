package com.example.sedmahra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Karta(val typ: TypKarty, val obrazok : Int) : Parcelable
