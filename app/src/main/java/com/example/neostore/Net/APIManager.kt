package com.example.neostore.Net

import com.example.neostore.Api.ApiManger
import com.example.neostore.Model.LoginRes
import retrofit2.Callback

class APIManager {

   /* fun login(email: String, password: String,callback: Callback<LoginRes>)
    {
       val apiClient =  ApiManger.create().userLogin(email,password)
        //apiClient.enqueue(callback)
    }*/

    fun register( first_name: String,
                  last_name: String,
                  email: String,
                  password: String,
                  confirm_password: String,
                  gender:String,
                  phone_no: String,callback: Callback<LoginRes>)
    {
        val apiClient = ApiManger.create().userRegister(first_name,last_name,email,
            password,confirm_password,gender, phone_no!!.toLong())
            apiClient.enqueue(callback)

    }


}