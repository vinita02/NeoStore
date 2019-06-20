package com.example.neostore.Contract

import com.example.neostore.BasePresenter
import com.example.neostore.BaseView

interface RegisterContract {
    interface View : BaseView {
        fun loginSuccess()
        fun loginFail()
        fun showEmailError()
        fun showPasswordError()
        fun showFirstNameError()
        fun showLastNameError()
        fun showConformPasswordError()
        fun showPhoneNoError()
    }
    interface Presenter: BasePresenter
    {
        fun validation(first_name: String,last_name: String,
                       email: String,password: String,
                       confirm_password: String,phone_no: String):Boolean
    }
}