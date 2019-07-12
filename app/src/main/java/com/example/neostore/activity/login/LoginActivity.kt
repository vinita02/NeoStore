package com.example.neostore.activity.login

import android.content.Intent
import android.os.Bundle
import com.example.neostore.base.BasePresenter
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import com.example.neostore.activity.ForgotPasswordActivity
import com.example.neostore.activity.home.HomeScreenActivity
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etPassword
import retrofit2.Callback as Callback1


class LoginActivity : BaseActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)

    override val getPresenter: BasePresenter
            get() =  presenter

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showEmailError() {
        etEmail.error = "Email Required"
        etEmail.requestFocus()
    }

    override fun showPasswordError() {
        etPassword.error = "Password Required"
        etPassword.requestFocus()
    }

    override fun loginSuccess(response: LoginRes?) {
        show("Successful")
        val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
        startActivity(intent)
    }

    override fun loginFail() {
        show("Fail Login")
    }

    override fun showError(message: String) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun logout() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnLogin.setOnClickListener() {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            var check =  presenter.validation(email,password)

            if(check){
                presenter.getResult(email,password)
            }

        }
        tvForgotPassword.setOnClickListener() {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        tvNoAcccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}