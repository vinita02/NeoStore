package com.example.neostore.activity.product_detail


import com.example.neostore.Data
import com.google.gson.annotations.SerializedName

data class SingleDataItem(
    @SerializedName("data")
    val data: Data = Data(),
    @SerializedName("status")
    val status: Int = 0
)