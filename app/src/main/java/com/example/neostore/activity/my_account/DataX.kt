package com.example.neostore.activity.my_account


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("product_categories")
    val productCategories: List<ProductCategory> = listOf(),
    @SerializedName("total_carts")
    val totalCarts: Int = 0,
    @SerializedName("total_orders")
    val totalOrders: Int = 0,
    @SerializedName("user_data")
    val userData: UserData = UserData()
)