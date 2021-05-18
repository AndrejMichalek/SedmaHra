package com.example.sedmahra

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Reprezentuje jedno kartu
 *
 * @property typ Typ karty
 * @property obrazok ID obrázka
 * @constructor Vytvorí kartu s daným obrázkom a typom
 */
@Parcelize
data class Karta(val typ: TypKarty, val obrazok : Int) : Parcelable, Serializable
