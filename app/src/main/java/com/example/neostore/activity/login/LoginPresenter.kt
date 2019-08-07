package com.example.neostore.activity.login

import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(view: LoginContract.View):
    LoginContract.Presenter

{
    var mView : LoginContract.View?  = null

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
        val apiClient =  ApiManger.create().userLogin(email,password)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                    t: LoginRes -> mView?.loginSuccess(t)},
                {t: Throwable -> mView?.loginFail()})
    }
}

