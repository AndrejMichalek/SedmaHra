package com.example.sedmahra

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Hrac(var kartyRuka: ArrayList<Karta> = ArrayList<Karta>(), var body:Int = 0) :Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        TODO("kartyRuka"),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hrac> {
        override fun createFromParcel(parcel: Parcel): Hrac {
            return Hrac(parcel)
        }

        override fun newArray(size: Int): Array<Hrac?> {
            return arrayOfNulls(size)
        }
    }
}
