package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.egemenbengi.yemeksepetidemo.R
import com.egemenbengi.yemeksepetidemo.databinding.FragmentMainMenuBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentNewAddressBinding

class MainMenu : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddAddress.setOnClickListener { addAddress(it) }
        binding.btnDonerID.setOnClickListener { getTavukDoner(it) }
        binding.btnPilavID.setOnClickListener { getPilav(it) }
        binding.btnPizzaID.setOnClickListener { getPizza(it) }
        binding.btnCigkofteID.setOnClickListener { getCigKofte(it) }
    }

    private fun addAddress(view: View){
        val action = MainMenuDirections.actionMainMenuToNewAddress()
        Navigation.findNavController(view).navigate(action)
    }

    private fun getTavukDoner(view: View){
        val category = "tavuk doner"
        actionToSelectedFoods(category, view)
    }

    private fun getPilav(view: View){
        val category = "pilav"
        actionToSelectedFoods(category, view)
    }

    private fun getPizza(view: View){
        val category = "pizza"
        actionToSelectedFoods(category, view)
    }

    private fun getCigKofte(view: View){
        val category = "cig kofte"
        actionToSelectedFoods(category, view)
    }

    private fun actionToSelectedFoods(category: String, view: View){
        val action = MainMenuDirections.actionMainMenuToSelectedFoods(category)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}