package com.egemenbengi.yemeksepetidemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.roomdb.AddressDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddressListViewModel(application: Application) : AndroidViewModel(application) {
    val addressList = MutableLiveData<List<AddressModel>>()

    private fun getDataFromRoom(){
        viewModelScope.launch {
            val dao = AddressDatabase(getApplication()).addressModelDao()
            withContext(Dispatchers.Main){
                addressList.value = dao.getAll()
            }
        }
    }

    fun getData(){
        getDataFromRoom()
    }

    fun deleteAddress(addressModel: AddressModel){
        viewModelScope.launch {
            AddressDatabase(getApplication()).addressModelDao().delete(addressModel)
        }
    }
}