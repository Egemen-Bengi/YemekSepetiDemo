package com.egemenbengi.yemeksepetidemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import com.egemenbengi.yemeksepetidemo.roomdb.AddressDatabase
import kotlinx.coroutines.launch

class NewAddressViewModel(application: Application) : AndroidViewModel(application) {

    fun insertAddress(addressModel: AddressModel){
        viewModelScope.launch {
            val dao = AddressDatabase(getApplication()).addressModelDao()
            dao.insert(addressModel)
        }
    }
}