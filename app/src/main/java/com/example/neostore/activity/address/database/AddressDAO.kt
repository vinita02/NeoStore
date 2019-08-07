package com.example.neostore.activity.address.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AddressDAO {

    @Insert
    fun saveAddress(add: AddressEntity)

    @Query("select * from  AddressEntity")
    fun getAddress():List<AddressEntity>
}






