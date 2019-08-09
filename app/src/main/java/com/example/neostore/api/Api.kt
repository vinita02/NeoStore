package com.example.neostore.api

import com.example.neostore.activity.address_list.AddressListResponse
import com.example.neostore.activity.my_cart.CartResponse
import com.example.neostore.activity.product_detail.QuantityResponse
import com.example.neostore.activity.edit_profile.EditProfileResponse
import com.example.neostore.activity.my_account.MyAccountResponse
import com.example.neostore.activity.product_detail.RatingResponse
import com.example.neostore.activity.product_detail.SingleDataItem
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.my_cart.DeleteCartResponse
import com.example.neostore.activity.order_details.OrderDetailsResponse
import com.example.neostore.activity.orderlist.OrderlistResponse
import com.example.neostore.activity.product.model.ProductResponse
import com.example.neostore.activity.reset_password.ResetResponse
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

    @GET(value = "api/users/getUserData")
    fun accountDetails(
        @Header("access_token") token: String
    ):Observable<MyAccountResponse>

    @FormUrlEncoded
    @POST(value = "api/users/update")
    fun editProfiles(
        @Header("access_token") token: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
        @Field("phone_no") phone_no: String,
        @Field("profile_pic") profile_pic: String?
    ):Observable<EditProfileResponse>

    @FormUrlEncoded
    @POST("api/users/change")
    fun changePassword(
        @Header("access_token") token: String,
        @Field("old_password") old_password: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ):Observable<ResetResponse>

    @FormUrlEncoded
    @POST(value = "api/addToCart")
    fun enterQuantity(
        @Header("access_token")access_token: String,
        @Field("product_id")product_id:String,
    @Field("quantity")quantity:String
    ):Observable<QuantityResponse>

    @GET("api/cart")
    fun cartList(
        @Header("access_token")access_token: String
    ):Observable<CartResponse>

    @FormUrlEncoded
    @POST(value = "api/order")
    fun addAddressList(
        @Header("access_token")access_token: String,
        @Field("address")address:String
    ):Observable<AddressListResponse>

    @GET(value = "api/orderList")
    fun displayListOfOrder(
        @Header("access_token")access_token: String
    ):Observable<OrderlistResponse>

    @GET(value = "api/orderDetail")
    fun displayOrderDetail(
        @Header("access_token")access_token: String,
        @Query("order_id")order_id:String
    ):Observable<OrderDetailsResponse>

    @FormUrlEncoded
    @POST(value = "api/deleteCart")
    fun deleteCartDetail(
        @Header("access_token")access_token: String,
        @Field("product_id")product_id: String
    ):Observable<DeleteCartResponse>
}