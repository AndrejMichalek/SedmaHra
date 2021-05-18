package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sedmahra.databinding.FragmentMenuBinding
import com.example.sedmahra.databinding.FragmentPravidlaBinding
import kotlinx.android.synthetic.main.fragment_pravidla.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Trieda obsahuje fragment s pravidlami hry
 *
 * @constructor Vytvoí fragment pravidiel hry
 */
class PravidlaFragment : Fragment() {
    private var poloha: Int = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentPravidlaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pravidla, container, false)
        binding.tlacidloVlavo.isEnabled = false

        val polohaNullova : Int? = savedInstanceState?.getInt("poloha")
        if(polohaNullova != null) {
            this.poloha = polohaNullova
        }
        this.prekresli(binding)

        binding.tlacidloVpravo.setOnClickListener() {
            poloha++;
            this.prekresli(binding)

        }
        binding.tlacidloVlavo.setOnClickListener() {
            poloha--
            this.prekresli(binding)

        }
        return binding.root
    }

    private fun prekresli(binding:FragmentPravidlaBinding) {
        binding.tlacidloVlavo.isEnabled = poloha != 0
        binding.tlacidloVpravo.isEnabled = poloha != 3


        when(poloha) {
            0->  {
                binding.imageViewPravidla.setImageResource(R.drawable.pravidla_uvod)
                binding.textViewPravidla.text = binding.root.resources.getString(R.string.pravidla_jedna_text)
            }
            1->  {
                binding.imageViewPravidla.setImageResource(R.drawable.pravidla_pouzi_rovnaku_kartu)
                binding.textViewPravidla.text = binding.root.resources.getString(R.string.pravidla_dva_text)
            }
            2-> {
                binding.imageViewPravidla.setImageResource(R.drawable.pravidla_pouzi_sedmicku)
                binding.textViewPravidla.text = binding.root.resources.getString(R.string.pravidla_tri_text)
            }
            3-> {
                binding.imageViewPravidla.setImageResource(R.drawable.pravidla_dorovnaj_do_parneho)
                binding.textViewPravidla.text = binding.root.resources.getString(R.string.pravidla_styri_text)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("poloha", poloha)
    }


}