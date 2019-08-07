package com.example.neostore.activity.order_details


import com.example.neostore.activity.order_details.DataX
import com.google.gson.annotations.SerializedName

data class OrderDetailsResponse(
    @SerializedName("data")
    val `data`: DataX = DataX(),
    @SerializedName("status")
    val status: Int = 0
)