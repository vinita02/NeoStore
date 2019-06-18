package com.example.neostore.Contract

import com.example.neostore.BasePresenter
import com.example.neostore.BaseView

interface LoginContract{
    interface View : BaseView{
        fun loginSuccess()
        fun loginFail()
        fun showEmailError()
        fun showPasswordError()
    }
    interface Presenter: BasePresenter
    {
        fun validation(email:String,password:String):Boolean
    }

}