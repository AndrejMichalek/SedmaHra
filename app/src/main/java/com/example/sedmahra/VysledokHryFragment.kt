package com.example.sedmahra

import android.content.Intent
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
    var ziskaneBody : Int = 0;
    lateinit var binding : FragmentVysledokHryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vysledok_hry, container, false)

        val ziskaneBodyNulovatelne : Int? = arguments?.getInt("ziskane body")
        if(ziskaneBodyNulovatelne != null) {
            this.ziskaneBody = ziskaneBodyNulovatelne

        }
        this.zobrazBodyAVyhru()

        val karty = arguments?.getParcelableArrayList<Karta>("ziskane karty")
        val kartyVstup : ArrayList<Karta>;
        if(karty != null) {
            kartyVstup = karty;
        } else {
            kartyVstup = ArrayList<Karta>()
        }
        val ziskaneKartyAdapter = ZiskaneKartyAdapter(kartyVstup)
        binding.recyclerViewZiskaneKarty.adapter = ziskaneKartyAdapter
        binding.recyclerViewZiskaneKarty.layoutManager= LinearLayoutManager(this.context)

        binding.zdielatTlacidlo.setOnClickListener() {
            this.zdielajVysledok()
        }

        return binding.root

    }
    private fun dajMiZdielaciIntent() : Intent {
        val intentVrat = Intent(Intent.ACTION_SEND)
        intentVrat.setType("text/plain").putExtra(Intent.EXTRA_TEXT, this.getVyhralPrehralText() + " " + this.getZiskaneBodyText())
        return intentVrat
    }

    private fun zdielajVysledok() {
        startActivity(this.dajMiZdielaciIntent())
    }



    fun zobrazBodyAVyhru() {
        //ziskane body text view

        binding.textViewZiskalSiBodov.text = this.getZiskaneBodyText()


        //vyhral / prehral /remiza text view

        binding.textViewVyhralPrehral.text = this.getVyhralPrehralText()

        //obrazok
        var obrazok: Int
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

    private fun getZiskaneBodyText() : String {
        if(ziskaneBody > 4 || ziskaneBody == 0) {
            return binding.root.resources.getString(R.string.ziskal_si_bodov, ziskaneBody)
        } else if (ziskaneBody == 1) {
            return binding.root.resources.getString(R.string.ziskal_si_bod, ziskaneBody)
        } else {
            return binding.root.resources.getString(R.string.ziskal_si_body, ziskaneBody)
        }
    }

    private fun getVyhralPrehralText() : String {
        if(ziskaneBody > 4) {
            return binding.root.resources.getString(R.string.vyhral_si)
        } else if(ziskaneBody == 4) {
            return binding.root.resources.getString(R.string.remiza)
        } else {
            return binding.root.resources.getString(R.string.prehral_si)
        }
    }




}