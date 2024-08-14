package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.egemenbengi.yemeksepetidemo.R
import com.egemenbengi.yemeksepetidemo.adapter.FoodsRowAdapter
import com.egemenbengi.yemeksepetidemo.adapter.SepetAdapter
import com.egemenbengi.yemeksepetidemo.databinding.FragmentMainMenuBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentSepetBinding
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.model.addressSingleton
import com.egemenbengi.yemeksepetidemo.model.sepetSingleton
import com.egemenbengi.yemeksepetidemo.viewmodel.AddressListViewModel

class Sepet : Fragment() {

    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddressListViewModel
    private var isEmpty: Boolean = true
    private val adapter = SepetAdapter(sepetSingleton.sepetFoods)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentSepetBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddressListViewModel::class.java]
        viewModel.getData()

        binding.recyclerSepetID.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerSepetID.adapter = adapter
        observeLiveData()
        if (sepetSingleton.sepetFoods.isNotEmpty()){
            binding.textSiparisYokID.visibility = View.GONE
            binding.btnSiprasVer.setOnClickListener { siparisVer(it) }
        } else {
            binding.textSiparisYokID.visibility = View.VISIBLE
            binding.btnSiprasVer.isEnabled = false
        }

    }

    private fun siparisVer(view: View){
        if (sepetSingleton.sepetFoods.isEmpty()){
            binding.textSiparisYokID.visibility = View.VISIBLE
            return
        }
        if (isEmpty){
            val action = SepetDirections.actionSepetToNewAddress()
            Navigation.findNavController(view).navigate(action)
        } else {
            val action = SepetDirections.actionSepetToAddressList()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun observeLiveData(){
        viewModel.addressList.observe(viewLifecycleOwner){addresses->
            addressSingleton.addressList = addresses as ArrayList<AddressModel>
            if (addresses.isEmpty()){
                isEmpty = true
            } else {
                isEmpty = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}