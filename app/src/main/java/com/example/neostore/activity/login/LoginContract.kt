package com.example.neostore.activity.login

import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.base.BasePresenter
import com.example.neostore.base.BaseView

interface LoginContract{
    interface View : BaseView {
        fun loginSuccess(response: LoginRes?)
        fun loginFail()
        fun showEmailError()
        fun showPasswordError()
    }
    interface Presenter: BasePresenter
    {
        fun validation(email:String,password:String):Boolean
    }

}