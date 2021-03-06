package com.example.sedmahra

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dvojica_kariet.view.*
import kotlinx.android.synthetic.main.historia_vysledok_hry.view.*

/**
 * Adaptér pre RecyclerView, ktorý zobrazuje históriu hier
 *
 * @property ulozeneHry ArrayList uložených hier
 * @constructor Vytvorí adaptér Histórie Hry
 */
class HistoriaHryAdapter (var ulozeneHry: ArrayList<VysledokHry>): RecyclerView.Adapter<HistoriaHryAdapter.HistoriaHryViewHolder>() {

    /**
     * Historia hry view holder
     *
     * @constructor - vytvorí view holder pre RecyclerView história hry
     *
     * @param itemView referencia na View, kde bude zobrazený
     */
    inner class HistoriaHryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaHryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historia_vysledok_hry, parent, false)
        return HistoriaHryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoriaHryViewHolder, position: Int) {
        holder.itemView.apply {
            image_view_smajlik.setImageResource(ulozeneHry[position].obrazokSmajlik)
            text_view_historia_ziskane_body.text = rootView.resources.getString(R.string.ziskane_body, ulozeneHry[position].ziskaneBody)
            text_view_historia_datum.text = rootView.resources.getString(R.string.datum, ulozeneHry[position].datum)
        }
    }

    override fun getItemCount(): Int {
        return ulozeneHry.size
    }
}