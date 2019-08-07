package com.example.neostore.activity.order_details


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("cost")
    val cost: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("order_details")
    val orderDetails: List<OrderDetail> = listOf()
)