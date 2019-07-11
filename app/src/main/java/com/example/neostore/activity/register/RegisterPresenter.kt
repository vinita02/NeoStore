package com.example.neostore.activity.register

import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.net.APICallback
import com.example.neostore.net.APIManager
import retrofit2.Call
import retrofit2.Response

class RegisterPresenter(view: RegisterContract.View):
    RegisterContract.Presenter {

    var mView : RegisterContract.View?  = null

    init {
        mView = view
    }
    override fun validation(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        confirm_password: String,
        phone_no: String
    ): Boolean {
        if(first_name.isEmpty())
        {
           mView?.showFirstNameError()
            return false
        }
        else if(last_name.isEmpty())
        {
            mView?.showLastNameError()
            return false
        }
        else if(email.isEmpty())
        {
            mView?.showEmailError()
            return false
        }
        else if(password.isEmpty())
        {
            mView?.showPasswordError()
            return false
        }
        else if(confirm_password.isEmpty())
        {
            mView?.showConformPasswordError()
            return false
        }
        else if(phone_no.isEmpty())
        {
            mView?.showPhoneNoError()
            return false
        }

          return true
    }

    override fun onStart() {
    }

    override fun onDestroy() {
        mView = null
    }



    fun getResult(first_name: String,last_name: String, email: String,password: String,confirm_password: String,gender : String,phone_no: String?)
    {

        APIManager().register(first_name,last_name,email,
            password,confirm_password,gender, phone_no.toString(),object :APICallback<LoginRes>(){

                override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                    mView?.loginSuccess()
                }
            })
    }
}

