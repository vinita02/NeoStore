package com.example.neostore.activity.product_detail


import com.example.neostore.Data
import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("data")
    val `data`: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)