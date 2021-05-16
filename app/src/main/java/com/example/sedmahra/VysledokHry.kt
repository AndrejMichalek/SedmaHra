package com.example.sedmahra

import java.io.Serializable

data class VysledokHry (val ziskaneBody: Int,val datum :String) :Serializable {
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