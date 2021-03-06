package com.example.sedmahra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.sedmahra.databinding.FragmentMenuBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Menu fragment
 *
 * @constructor Create empty Menu fragment
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMenuBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        val klucKNovejHre : String = "nova_hra"

        binding.hrajButton.setOnClickListener() { view:View ->
            view.findNavController().navigate(R.id.menuFragment_to_hraFragment)
        }
        binding.novaHraButton.setOnClickListener() {view:View->
            val balicek : Bundle = Bundle();
            balicek.putBoolean(klucKNovejHre, true)
            view.findNavController().navigate(R.id.menuFragment_to_hraFragment, balicek)
        }

        binding.pravidlaHryButton.setOnClickListener() {view:View->
            view.findNavController().navigate(R.id.action_menuFragment_to_pravidlaFragment)
        }

        binding.historiaHryButton.setOnClickListener() {view:View->
            view.findNavController().navigate(R.id.action_menuFragment_to_historiaFragment)
        }

        return binding.root
    }




    /*companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}