package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sedmahra.databinding.FragmentPravidlaBinding
import com.example.sedmahra.databinding.FragmentVysledokHryBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VysledokHryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VysledokHryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentVysledokHryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vysledok_hry, container, false)
        val karty = arguments?.getParcelableArrayList<Karta>("ziskane karty")
        var kartyVstup : ArrayList<Karta>;
        if(karty != null) {
            kartyVstup = karty;
        } else {
            kartyVstup = ArrayList<Karta>()
        }
        val ziskaneKartyAdapter = ZiskaneKartyAdapter(kartyVstup)
        binding.recyclerView2.adapter = ziskaneKartyAdapter
        binding.recyclerView2.layoutManager= LinearLayoutManager(this.context)

        return binding.root

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

}