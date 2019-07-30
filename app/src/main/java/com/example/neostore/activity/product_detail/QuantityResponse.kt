package com.example.neostore.activity.product_detail


import com.google.gson.annotations.SerializedName

data class QuantityResponse(
    @SerializedName("data")
    val `data`: Boolean = false,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("total_carts")
    val totalCarts: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)