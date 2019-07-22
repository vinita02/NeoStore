package com.example.neostore.activity.product_detail

import com.google.gson.annotations.SerializedName

data class ProductImagesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("modified")
	val modified: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)