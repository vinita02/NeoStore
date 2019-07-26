package com.example.neostore.activity.my_account


import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("created")
    val created: String = "",
    @SerializedName("icon_image")
    val iconImage: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("name")
    val name: String = ""
)