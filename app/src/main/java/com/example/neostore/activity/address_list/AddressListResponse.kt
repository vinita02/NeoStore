package com.example.neostore.activity.address_list


import com.google.gson.annotations.SerializedName

data class AddressListResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)