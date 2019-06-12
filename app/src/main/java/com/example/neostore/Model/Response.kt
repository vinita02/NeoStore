package com.example.neostore.Model

data class Response(
	val data: DataItem? = null,
	val message: String? = null,
	val status: Int? = null,
	val userMsg: String? = null
)
