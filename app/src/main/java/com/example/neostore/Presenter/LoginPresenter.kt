package com.example.neostore.Presenter

import com.example.neostore.Contract.LoginContract
import com.example.neostore.Model.LoginRes
import com.example.neostore.Net.APICallback
import com.example.neostore.Net.APIManager

class LoginPresenter(view:LoginContract.View):LoginContract.Presenter

{
    var mView :LoginContract.View?  = null

    init {
        mView = view
    }

    override fun onDestroy() {
        mView = null
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
        APIManager().login(email, password, object : APICallback<LoginRes>()
        {
            override fun onSuccess(code: Int?, response: LoginRes?) {
                when(code){
                    200 -> {mView?.loginSuccess(response)}
                    401 -> {mView?.loginFail()}
                }

            }
            })
        }

    }

