package com.example.neostore.activity.my_cart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("cost")
    val cost: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("product_category")
    val productCategory: String = "",
    @SerializedName("product_images")
    val productImages: String = "",
    @SerializedName("sub_total")
    val subTotal: Int = 0
)