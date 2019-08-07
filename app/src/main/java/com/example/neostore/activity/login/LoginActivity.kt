package com.example.neostore.activity.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.UnicodeSet.EMPTY
import android.os.Bundle
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.provider.Telephony.Carriers.PASSWORD
import android.widget.Toast
import com.example.neostore.*
import com.example.neostore.base.BasePresenter
import com.example.neostore.base.BaseActivity
import com.example.neostore.activity.ForgotPasswordActivity
import com.example.neostore.activity.home.HomeScreenActivity
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etPassword
import retrofit2.Callback as Callback1


class LoginActivity : BaseActivity(), LoginContract.View {

    val sharedPrefFile = "kotlinsharedpreference"
    val presenter = LoginPresenter(this)

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

        val sharedPreferences:SharedPreferences = getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        editor.putString( com.example.neostore.EMAIL,email)
        editor.putString(com.example.neostore.PASSWORD,password)
        editor.putString(AccessToken,response?.data?.accessToken)
        editor.putBoolean(LOGGEDIN,true)
        editor.apply()
        editor.commit()
        show("Successful")
        Toast.makeText(this@LoginActivity, "Saves", Toast.LENGTH_LONG).show()

        val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
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

        val settings = getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val isLoggedIn = settings.getBoolean(LOGGEDIN, false)
        if(isLoggedIn )
        {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener() {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            var check =  presenter.validation(email,password)
            if(check){
                presenter.getResult(email,password)
            } }

        tvForgotPassword.setOnClickListener() {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent) }

        tvNoAcccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
