package com.example.neostore.Presenter

import com.example.neostore.Api.Api
import com.example.neostore.Api.ApiManger
import com.example.neostore.Contract.LoginContract
import com.example.neostore.Model.LoginRes
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(view:LoginContract.View):LoginContract.Presenter

{
    override fun onDestroy() {
        mView = null
    }

    var mView :LoginContract.View?  = null

    init {
        mView = view
    }

    override fun onStart() {

    }

    override fun validation(email:String,password:String):Boolean {

        if(email.isEmpty())
        {
            mView?.showEmailError()
            return false
        }
        if(password.isEmpty())
        {
            mView?.showPasswordError()
            return false
        }
        return true
    }


    fun getResult(email: String,password: String)
    {
        ApiManger.create()
            .userLogin(email, password)
            .enqueue(object : Callback<LoginRes>{
                override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                    mView?.loginFail()
                }

                override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                    mView?.loginSuccess()
                 }

            })
    }




}