package com.example.neostore.activity.my_account


import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("country_id")
    val countryId: Any = Any(),
    @SerializedName("created")
    val created: String = "",
    @SerializedName("dob")
    val dob: Any = Any(),
    @SerializedName("email")
    val email: String = "",
    @SerializedName("first_name")
    val firstName: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("last_name")
    val lastName: String = "",
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("phone_no")
    val phoneNo: String = "",
    @SerializedName("profile_pic")
    val profilePic: Any = Any(),
    @SerializedName("role_id")
    val roleId: Int = 0,
    @SerializedName("username")
    val username: String = ""
)