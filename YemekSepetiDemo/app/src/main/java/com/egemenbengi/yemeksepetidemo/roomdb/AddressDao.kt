package com.egemenbengi.yemeksepetidemo.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.egemenbengi.yemeksepetidemo.model.AddressModel

@Dao
interface AddressDao {
    @Insert
    suspend fun insert(addressModel: AddressModel)

    @Query("SELECT * FROM AddressModel")
    suspend fun getAll(): List<AddressModel>

    @Delete
    suspend fun delete(addressModel: AddressModel)
}