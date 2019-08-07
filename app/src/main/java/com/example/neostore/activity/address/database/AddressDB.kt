package com.example.neostore.activity.address.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(AddressEntity::class)],version = 2)
 abstract class AddressDB :RoomDatabase(){

    abstract fun addressDao(): AddressDAO


    companion object{

        var sInstance: AddressDB? = null

        @Synchronized
        fun getInstance(context: Context): AddressDB {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AddressDB::class.java, "Address Details")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }

    }

}

