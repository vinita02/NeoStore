package com.example.neostore.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.neostore.Api.ApiManger
import com.example.neostore.Model.LoginRes
import com.example.neostore.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etPassword
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback1


class LoginActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        btnLogin.setOnClickListener() {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etEmail.error = "Email Required"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Password Required"
                etPassword.requestFocus()
                return@setOnClickListener
            }


            ApiManger.create()
                .userLogin(email, password)
                .enqueue(object : retrofit2.Callback<LoginRes> {
                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Failure", Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {

                        if(response.body() != null)
                        {
                           Toast.makeText(this@LoginActivity,"Successful",Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Toast.makeText(this@LoginActivity,"fail",Toast.LENGTH_LONG).show()
                        }
                        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                        startActivity(intent)
                    }

                })


        }
        tvForgotPassword.setOnClickListener() {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        tvNoAcccount.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
