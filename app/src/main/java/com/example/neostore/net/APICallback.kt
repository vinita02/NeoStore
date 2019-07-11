package com.example.neostore.net

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class APICallback<T>(): Callback<T> {
    var noInternet:Boolean = false

    override fun onFailure(call: Call<T>, t: Throwable) {
        if(noInternet){
            //get data from cache or local db
            onSuccess(200,null)
        }

    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.code(),response.body())
    }

    open fun onSuccess(code: Int?, response: T?) {}
}