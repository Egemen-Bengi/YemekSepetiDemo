package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.egemenbengi.yemeksepetidemo.R
import com.egemenbengi.yemeksepetidemo.databinding.FragmentAddressDetailBinding
import com.egemenbengi.yemeksepetidemo.databinding.FragmentSepetBinding
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.model.addressSingleton
import com.egemenbengi.yemeksepetidemo.viewmodel.AddressListViewModel

class AddressDetail : Fragment() {

    private var _binding: FragmentAddressDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddressListViewModel
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var name: String
    private lateinit var surname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddressDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddressListViewModel::class.java]
        arguments?.let {
            address = AddressDetailArgs.fromBundle(it).Address
            phone = AddressDetailArgs.fromBundle(it).Phone
            name = AddressDetailArgs.fromBundle(it).Name
            surname = AddressDetailArgs.fromBundle(it).Surname
        }
        binding.textAddressDetailID.text = address
        binding.textNameDetailID.text = name.plus(" $surname")
        binding.textPhone.text = phone

        binding.btnDeleteDetailID.setOnClickListener {
            val deletedAddress = AddressModel(name, surname, address, phone)
            addressSingleton.addressList.remove(deletedAddress)
            viewModel.deleteAddress(deletedAddress)
            backPress()
        }

        binding.btnSelectDetailID.setOnClickListener {
            Toast.makeText(requireContext(), "Sipariş ${address}ine yola çıktı", Toast.LENGTH_LONG).show()
        }
    }

    private fun backPress(){
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}