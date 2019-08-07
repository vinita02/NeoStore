package com.example.neostore.activity.address.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AddressEntity")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "ADDRESS")var address:String,
    @ColumnInfo(name = "LANDMARK")var landmark:String,
    @ColumnInfo(name = "CITY")var  city:String,
    @ColumnInfo(name = "STATE")var state:String,
    @ColumnInfo(name = "ZIP CODE")var zipcode:String,
    @ColumnInfo(name = "COUNTRY")var country:String
)



