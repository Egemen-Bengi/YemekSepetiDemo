package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.egemenbengi.yemeksepetidemo.R
import com.egemenbengi.yemeksepetidemo.databinding.FragmentNewAddressBinding
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.viewmodel.NewAddressViewModel

class NewAddress : Fragment() {

    private var _binding: FragmentNewAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewAddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewAddressBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewAddressViewModel::class.java]
        binding.btnSaveID.setOnClickListener { save(it) }
    }

    private fun save(view: View){
        val name = binding.textNameEditID.text.toString()
        val surname = binding.textSurnameEditID.text.toString()
        val address = binding.textAddressEditID.text.toString()
        val phone = binding.textPhoneEditID.text.toString()

        val addressModel = AddressModel(name, surname, address, phone)
        viewModel.insertAddress(addressModel)
        Toast.makeText(requireContext(), "Adres kaydedildi", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}