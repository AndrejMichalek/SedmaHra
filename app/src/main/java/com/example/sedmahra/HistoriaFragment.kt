package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedmahra.databinding.FragmentHistoriaBinding
import com.example.sedmahra.databinding.FragmentPravidlaBinding
import kotlinx.android.synthetic.main.fragment_historia.*


/**
 * Historia fragment - trieda obsahuje fragment spracujúci históriu hry
 *
 * @constructor Vytvorí HistoriaFragment
 */
class HistoriaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHistoriaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_historia, container, false)

        val nacitavac = UkladanieVysledkovHry()
        val vysledkyHier = nacitavac.dajVsetky(this.context)

        val historiaAdapter = HistoriaHryAdapter(vysledkyHier)
        binding.historiaRecyclerView.adapter = historiaAdapter
        binding.historiaRecyclerView.layoutManager = LinearLayoutManager(this.context)


        binding.vymazHistoriuButton.setOnClickListener() {
            nacitavac.zmazVsetky(this.context)
            vysledkyHier.clear()
            historiaAdapter.notifyDataSetChanged()

        }


        return binding.root
    }


}