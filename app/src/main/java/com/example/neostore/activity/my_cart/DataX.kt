package com.example.neostore.activity.my_cart


import com.example.neostore.activity.my_cart.Product
import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("product")
    val product: Product = Product(),
    @SerializedName("product_id")
    val productId: Int = 0,
    @SerializedName("quantity")
    val quantity: Int = 0
)