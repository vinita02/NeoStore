package com.example.neostore.activity.my_account


import com.example.neostore.activity.my_account.DataX
import com.google.gson.annotations.SerializedName

data class MyAccountResponse(
    @SerializedName("data")
    val data : DataX = DataX(),
    @SerializedName("status")
    val status: Int = 0
)