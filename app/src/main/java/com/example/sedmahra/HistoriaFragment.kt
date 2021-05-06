package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.sedmahra.databinding.FragmentHistoriaBinding
import com.example.sedmahra.databinding.FragmentPravidlaBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HistoriaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoriaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHistoriaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_historia, container, false)





        return binding.root
    }


}