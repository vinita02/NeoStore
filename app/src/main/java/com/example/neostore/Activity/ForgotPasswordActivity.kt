package com.example.neostore.Activity

import android.content.Intent
import android.os.Bundle
import com.example.neostore.R
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : BaseActivity() {

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


}


