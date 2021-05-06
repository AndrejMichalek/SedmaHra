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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PravidlaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PravidlaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentPravidlaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pravidla, container, false)





        return binding.root


    }


}