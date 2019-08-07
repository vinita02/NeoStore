package com.example.neostore.activity.orderlist


import com.google.gson.annotations.SerializedName

data class OrderlistResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)