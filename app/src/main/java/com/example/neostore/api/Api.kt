package com.example.neostore.api

import com.example.neostore.RatingResponse
import com.example.neostore.activity.product_detail.SingleDataItem
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.product.model.ProductResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface Api {

@FormUrlEncoded
    @POST(value ="api/users/login")
    fun userLogin(
    @Field(value = "email")email:String,
    @Field(value = "password")password:String
    ):Observable<LoginRes>


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


    @GET(value = "api/products/getList")
    fun productList(
        @Query(value = "product_category_id")product_category_id:String,
        @Query(value = "limit ")limit:String,
        @Query(value = "page ")page:String
    ):Observable<ProductResponse>

    @GET(value = "api/products/getDetail")
    fun productDetails(
        @Query(value = "product_id")product_id:String
    ):Observable<SingleDataItem>

    @FormUrlEncoded
    @POST(value = "api/products/setRating")
    fun productRating(
        @Field(value = "product_id")product_id: String,
        @Field(value = "rating")rating: Int
    ):Observable<RatingResponse>
}