package com.egemenbengi.yemeksepetidemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressModel(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo("surname") var surname: String,
    @ColumnInfo("address") var address: String,
    @ColumnInfo("phone") var phone: String
){
    @PrimaryKey(autoGenerate = true) var uuid: Int = 0
}