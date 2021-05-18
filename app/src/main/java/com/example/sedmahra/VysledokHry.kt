package com.example.sedmahra

import java.io.Serializable

/**
 * Výsledok hry
 *
 * @property ziskaneBody Body získané hráčom v danej hre
 * @property datum Dátum vo forme reťazca, kedy sa hra uskutočnila
 * @constructor Vytvorí objekt reprezentujúci výsledok hry
 */
data class VysledokHry (val ziskaneBody: Int, val datum :String) :Serializable {
    /**
     * Smajlík pre príslušný výsledok hry podľa získaných bodov
     */
    val obrazokSmajlik : Int
    get() {
        if(this.ziskaneBody == 8) {
            return R.drawable.smajlik_vyhral_vsetko
        } else if(this.ziskaneBody >4) {
            return R.drawable.smajlik_vyhral
        } else if(this.ziskaneBody ==4) {
           return R.drawable.smajlik_remiza
        } else if(this.ziskaneBody == 0) {
            return R.drawable.smajlik_prehral_vsetko
        } else {
            return R.drawable.smajlik_prehral
        }
    }
}