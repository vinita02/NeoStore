package com.example.neostore.Model

import android.databinding.BaseObservable

data class UserData  (
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val confirm_password: String,
    val gender: String,
    val phone_no: Long

)