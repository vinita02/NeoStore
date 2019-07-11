package com.example.neostore.activity.product.model


import com.example.neostore.activity.product.model.ProductItem
import com.google.gson.annotations.SerializedName


data class ProductResponse(

    @field:SerializedName("data")
	val data1: List<ProductItem>? = null,

    @field:SerializedName("status")
	val status: Int? = null
)