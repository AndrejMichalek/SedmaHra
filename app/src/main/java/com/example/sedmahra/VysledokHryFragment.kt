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

        val ziskaneBody : Int? = arguments?.getInt("ziskane body")
        if(ziskaneBody != null) {
            this.zobrazBodyAVyhru(ziskaneBody, binding)
        }


        val karty = arguments?.getParcelableArrayList<Karta>("ziskane karty")
        var kartyVstup : ArrayList<Karta>;
        if(karty != null) {
            kartyVstup = karty;
        } else {
            kartyVstup = ArrayList<Karta>()
        }
        val ziskaneKartyAdapter = ZiskaneKartyAdapter(kartyVstup)
        binding.recyclerViewZiskaneKarty.adapter = ziskaneKartyAdapter
        binding.recyclerViewZiskaneKarty.layoutManager= LinearLayoutManager(this.context)

        return binding.root

    }

    fun zobrazBodyAVyhru(ziskaneBody: Int, binding : FragmentVysledokHryBinding) {
        //ziskane body text view
        if(ziskaneBody > 4 || ziskaneBody == 0) {
            binding.textViewZiskalSiBodov.text = binding.root.resources.getString(R.string.ziskal_si_bodov, ziskaneBody)
        } else if (ziskaneBody == 1) {
            binding.textViewZiskalSiBodov.text = binding.root.resources.getString(R.string.ziskal_si_bod, ziskaneBody)
        } else {
            binding.textViewZiskalSiBodov.text = binding.root.resources.getString(R.string.ziskal_si_body, ziskaneBody)
        }

        //vyhral / prehral /remiza text view
        if(ziskaneBody > 4) {
            binding.textViewVyhralPrehral.text = binding.root.resources.getString(R.string.vyhral_si)
        } else if(ziskaneBody == 4) {
            binding.textViewVyhralPrehral.text = binding.root.resources.getString(R.string.remiza)
        } else {
            binding.textViewVyhralPrehral.text = binding.root.resources.getString(R.string.prehral_si)
        }
        //obrazok
        var obrazok: Int = R.drawable.smajlik_vyhral
        if(ziskaneBody == 8) {
            obrazok = R.drawable.smajlik_vyhral_vsetko
        } else if(ziskaneBody >4) {
            obrazok = R.drawable.smajlik_vyhral
        } else if(ziskaneBody ==4) {
            obrazok = R.drawable.smajlik_remiza
        } else if (ziskaneBody < 4) {
            obrazok = R.drawable.smajlik_prehral
        } else {
            obrazok = R.drawable.smajlik_prehral
        }
        binding.imageViewVyhralPrehral.setImageResource(obrazok)

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

}