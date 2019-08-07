package com.example.neostore.activity.orderlist


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cost")
    val cost: Int = 0,
    @SerializedName("created")
    val created: String = "",
    @SerializedName("id")
    val id: Int = 0
)