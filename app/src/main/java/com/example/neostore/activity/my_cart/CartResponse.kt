package com.example.neostore.activity.my_cart


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("data")
    val data : ArrayList<DataX> = arrayListOf(),
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)