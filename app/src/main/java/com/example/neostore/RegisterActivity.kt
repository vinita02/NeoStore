package com.example.neostore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun getLayout(): Int {
       return R.layout.activity_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SetToolbar
        setToolbar("Register")
        btnRegister.setOnClickListener() {
            val intent = Intent(this@RegisterActivity, HomeScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
