package com.egemenbengi.yemeksepetidemo.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.egemenbengi.yemeksepetidemo.model.AddressModel
import kotlin.concurrent.Volatile

@Database(entities = [AddressModel::class], version = 2)
abstract class AddressDatabase: RoomDatabase() {
    abstract fun addressModelDao(): AddressDao

    companion object{
        @Volatile
        private var instance: AddressDatabase? = null
        private val lock = Any()

        operator fun invoke(context:Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AddressDatabase::class.java,
            "AddressDatabase"
        ).build()
    }
}