package com.example.neostore.Api

import com.example.neostore.Model.LoginRes
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

@FormUrlEncoded
    @POST(value ="api/users/login")
    fun userLogin(
    @Field(value = "email")email:String,
    @Field(value = "password")password:String
    ):Call<LoginRes>


    @FormUrlEncoded
    @POST(value = "api/users/register")
    fun userRegister(
        @Field(value = "first_name")first_name:String,
        @Field(value = "last_name")last_name:String,
        @Field(value = "email")email:String,
        @Field(value = "password")password:String,
        @Field(value = "confirm_password")confirm_password:String,
        @Field(value = "gender")gender:String,
        @Field(value = "phone_no")phone_no:Long


    ):Call<LoginRes>
}