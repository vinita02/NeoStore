package com.example.neostore.activity.reset_password


import com.google.gson.annotations.SerializedName

data class ResetResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)