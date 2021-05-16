package com.example.sedmahra

import android.content.Context
import android.os.Bundle
import android.text.BoringLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sedmahra.databinding.FragmentHraBinding
import com.example.sedmahra.databinding.FragmentPravidlaBinding
import java.io.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HraFragment : Fragment() {
    lateinit var hra: Hra

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding : FragmentHraBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hra, container, false)

        /*var file = File(context?.filesDir,"ulozenaHra1")
        if(file.exists()) {
            file.delete()
        }*/
        val novaHra: Boolean? = arguments?.getBoolean("nova_hra")
        if(novaHra == true) {
            var file = File(context?.filesDir,"ulozenaHra1")
            if(file.exists()) {
                file.delete()
            }
            this.hra = Hra(binding, false)
        }
        val hraUlozena : Boolean? = savedInstanceState?.getBoolean("hraUlozena")
        if(hraUlozena == false || hraUlozena == null) {
            var nacitalaSa : Boolean = this.nacitajHruZoSuboru(binding)
            if(!nacitalaSa) {
                hra = Hra(binding, false);
            }

        } else {
            this.nacitajHru(savedInstanceState, binding)
        }

        return binding.root
    }

    override fun onDestroyView() {
        this.ulozHruDoSuboru()
        super.onDestroyView()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        ulozHru(outState)

    }
    private fun nacitajHru(savedInstanceState: Bundle?, binding : FragmentHraBinding) {


            var hrac : Hrac? = savedInstanceState?.getParcelable<Hrac>("hrac")
            var protihrac : Hrac? = savedInstanceState?.getParcelable<Hrac>("protihrac")
            var hracVyhralKolo : Boolean? = savedInstanceState?.getBoolean("hracVyhralKolo")
            var karty : ArrayList<Karta>? = savedInstanceState?.getParcelableArrayList("karty")
            var kartyNaStole : ArrayList<Karta>? = savedInstanceState?.getParcelableArrayList("kartyNaStole")
            var ziskaneKartyHrac : ArrayList<Karta>? = savedInstanceState?.getParcelableArrayList("ziskaneKartyHrac")
            if(hrac != null && protihrac != null && hracVyhralKolo != null && karty != null && kartyNaStole != null && ziskaneKartyHrac != null) {
                hra = Hra(binding, true, hrac, protihrac, karty, ziskaneKartyHrac, hracVyhralKolo, kartyNaStole)
            }

    }

    private fun ulozHru(outState: Bundle) {
        outState.putParcelable("hrac", hra.hrac)
        outState.putParcelable("protihrac", hra.protihrac)
        outState.putBoolean("hracVyhralKolo", hra.hracVyhralKolo)
        outState.putParcelableArrayList("karty", hra.karty)
        outState.putParcelableArrayList("kartyNaStole", hra.kartyNaStole)
        outState.putParcelableArrayList("ziskaneKartyHrac", hra.ziskaneKartyHrac);
        outState.putBoolean("hraUlozena", true)
    }

    private fun ulozHruDoSuboru() {

        hra.binding = null;

        ObjectOutputStream(context?.openFileOutput("ulozenaHra1", Context.MODE_PRIVATE)).use { it ->
            it.writeObject(hra)

        }
    }


    private fun nacitajHruZoSuboru(binding : FragmentHraBinding): Boolean {
        try {
            ObjectInputStream(context?.openFileInput("ulozenaHra1")).use { it ->
                val nacitanaHra = it.readObject()
                when (nacitanaHra) {
                    is Hra -> {
                        this.hra = nacitanaHra
                        if(this.hra.karty.size > 0) {
                            this.hra.binding = binding;
                            this.hra.aktualizujZobrazenieKariet()
                            this.hra.aktualizujZobrazenieTextu()
                            this.hra.nastavListenery()
                            return true
                        }
                        else {
                            this.hra = Hra(binding, false)
                            return true
                        }
                    }
                    else -> return false
                }
            }
        } catch (e : FileNotFoundException) {
            return false;
        }

    }



}