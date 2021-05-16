package com.example.sedmahra

import android.content.Context
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class UkladanieVysledkovHry {
    private val nazovSuboru: String = "subor_historia_hry"

    fun zmazVsetky(context : Context?) {
        val prazdnyArrayList : ArrayList<VysledokHry> = ArrayList<VysledokHry>()

        ObjectOutputStream(context?.openFileOutput(nazovSuboru, Context.MODE_PRIVATE)).use { it ->
            it.writeObject(prazdnyArrayList)
        }
    }

    fun zapisNovy(vysledok: VysledokHry, context : Context?) {
        val vsetkyVysledky = this.dajVsetky(context)

        vsetkyVysledky.add(vysledok)

        ObjectOutputStream(context?.openFileOutput(nazovSuboru, Context.MODE_PRIVATE)).use { it ->
            it.writeObject(vsetkyVysledky)
        }
    }

    fun dajVsetky(context : Context?) : ArrayList<VysledokHry> {
        var vsetkyVysledky : ArrayList<VysledokHry>
        try {
            ObjectInputStream(context?.openFileInput(this.nazovSuboru)).use { it ->
                var nacitanyObjekt = it.readObject()
                when (nacitanyObjekt) {
                    is ArrayList<*> -> {
                        vsetkyVysledky = nacitanyObjekt as ArrayList<VysledokHry>
                    }
                    else -> vsetkyVysledky = ArrayList<VysledokHry>()
                }
            }
        } catch (e : FileNotFoundException) {
            vsetkyVysledky = ArrayList<VysledokHry>()
        }
        return vsetkyVysledky
    }



}