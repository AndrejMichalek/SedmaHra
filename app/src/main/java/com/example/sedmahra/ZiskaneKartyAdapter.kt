package com.example.sedmahra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dvojica_kariet.view.*


/**
 * Adaptér pre RecyclerView získaných kariet
 *
 * @property karty ArrayList získaných kariet
 * @constructor Vytvorí prázdny adaptér získaných kariet
 */
class ZiskaneKartyAdapter(var karty: ArrayList<Karta>) : RecyclerView.Adapter<ZiskaneKartyAdapter.ZiskaneKartyViewHolder>() {
    /**
     * Ziskane karty view holder pre recycler view získaných kariet
     *
     * @constructor
     *
     * @param itemView referencia na príslušný view
     */
    inner class ZiskaneKartyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZiskaneKartyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dvojica_kariet, parent, false)
        return ZiskaneKartyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZiskaneKartyViewHolder, position: Int) {
       holder.itemView.apply {
           lava_karta.setImageResource(karty[position].obrazok)
           prava_karta.setImageResource(karty[position + karty.size/2].obrazok)
       }

    }
    override fun getItemCount(): Int {
        return karty.size/2;
    }
}