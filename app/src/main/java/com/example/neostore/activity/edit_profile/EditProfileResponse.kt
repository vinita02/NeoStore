package com.example.neostore.activity.edit_profile


import com.example.neostore.activity.edit_profile.DataX
import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("data")
    val `data`: DataX = DataX(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user_msg")
    val userMsg: String = ""
)