package com.example.neostore.activity.order_details


import com.google.gson.annotations.SerializedName

data class OrderDetail(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("order_id")
    val orderId: Int = 0,
    @SerializedName("prod_cat_name")
    val prodCatName: String = "",
    @SerializedName("prod_image")
    val prodImage: String = "",
    @SerializedName("prod_name")
    val prodName: String = "",
    @SerializedName("product_id")
    val productId: Int = 0,
    @SerializedName("quantity")
    val quantity: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)