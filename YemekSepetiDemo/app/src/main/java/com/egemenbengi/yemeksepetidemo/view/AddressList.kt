package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.egemenbengi.yemeksepetidemo.R
import com.egemenbengi.yemeksepetidemo.adapter.AddressAdapter
import com.egemenbengi.yemeksepetidemo.databinding.AddressListRowBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentAddressListBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentMainMenuBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentSepetBinding
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.model.addressSingleton
import com.egemenbengi.yemeksepetidemo.viewmodel.AddressListViewModel

class AddressList : Fragment() {

    private var _binding: FragmentAddressListBinding? = null
    private val binding get() = _binding!!
    private var adapter = AddressAdapter(addressSingleton.addressList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddressListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerAddressesID.layoutManager = LinearLayoutManager(requireContext())
        adapter.notifyDataSetChanged()
        binding.recyclerAddressesID.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}