package com.example.sedmahra

import android.content.Context
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Ukladanie vysledkov hry do súboru
 *
 * @constructor Vytvorí objekt ukladača do súboru
 */
class UkladanieVysledkovHry {
    private val nazovSuboru: String = "subor_historia_hry"

    /**
     * Zmaže všetky výsledky hier
     *
     * @param context
     */
    fun zmazVsetky(context : Context?) {
        val prazdnyArrayList : ArrayList<VysledokHry> = ArrayList<VysledokHry>()

        ObjectOutputStream(context?.openFileOutput(nazovSuboru, Context.MODE_PRIVATE)).use { it ->
            it.writeObject(prazdnyArrayList)
        }
    }

    /**
     * Zapíše nový výsledok
     *
     * @param vysledok výsledok, ktorý treba zapísať
     * @param context kontext z viewu, ktorý chce zapisovať
     */
    fun zapisNovy(vysledok: VysledokHry, context : Context?) {
        val vsetkyVysledky = this.dajVsetky(context)

        vsetkyVysledky.add(vysledok)

        ObjectOutputStream(context?.openFileOutput(nazovSuboru, Context.MODE_PRIVATE)).use { it ->
            it.writeObject(vsetkyVysledky)
        }
    }

    /**
     * Načíta všetky uložené hry zo súboru, ak žiadne nie sú, vráti prázdny arraylist
     *
     * @param context kontext z viewu, ktorý chce zapisovať
     * @return ArrayList uložených výsledkov hier
     */
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