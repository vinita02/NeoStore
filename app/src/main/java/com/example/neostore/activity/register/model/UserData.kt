package com.example.neostore.activity.register.model

data class UserData  (
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val confirm_password: String,
    val gender: String,
    val phone_no: Long

)