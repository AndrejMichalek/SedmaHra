package com.example.sedmahra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Karta(val typ: TypKarty, val obrazok : Int) : Parcelable, Serializable
