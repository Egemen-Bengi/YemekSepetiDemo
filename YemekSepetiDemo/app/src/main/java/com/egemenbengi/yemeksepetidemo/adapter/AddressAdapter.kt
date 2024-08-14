package com.egemenbengi.yemeksepetidemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.egemenbengi.yemeksepetidemo.databinding.AddressListRowBinding
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.model.addressSingleton
import com.egemenbengi.yemeksepetidemo.view.AddressListDirections

class AddressAdapter(val addressList: ArrayList<AddressModel>): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    
    class AddressViewHolder(val binding: AddressListRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = AddressListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.binding.textAddressListRowID.text = addressList[position].address
        holder.itemView.setOnClickListener {
            val action = AddressListDirections.actionAddressListToAddressDetail(addressList[position].address,
                addressList[position].phone,
                addressList[position].name,
                addressList[position].surname)
            Navigation.findNavController(it).navigate(action)
        }
    }
}