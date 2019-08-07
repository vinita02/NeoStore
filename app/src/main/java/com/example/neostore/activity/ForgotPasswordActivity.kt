package com.example.neostore.activity

import android.content.Intent
import android.os.Bundle
import com.example.neostore.base.BasePresenter
import com.example.neostore.activity.login.LoginContract
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.login.LoginPresenter
import com.example.neostore.R
import com.example.neostore.activity.register.RegisterActivity
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : BaseActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)


    override fun getLayout(): Int {
        return R.layout.activity_forgot_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnLogin.setOnClickListener() {
            val intent = Intent(this@ForgotPasswordActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun loginSuccess(response: LoginRes?) {
    }
    override fun loginFail() {
    }
    override fun showEmailError() {
    }
    override fun showPasswordError() {
    }
    override fun showError(message: String) {
    }
    override fun showLoading() {
    }
    override fun hideLoading() {
    }
    override fun logout() {
    }
}


