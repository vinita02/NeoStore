package com.example.neostore.net

import com.example.neostore.api.ApiManger
import com.example.neostore.activity.login.model.LoginRes
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