package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        return binding.root
    }

}