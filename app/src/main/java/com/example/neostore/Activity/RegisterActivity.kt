package com.example.neostore.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.neostore.Api.ApiManger
import com.example.neostore.Model.LoginRes
import com.example.neostore.R
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : BaseActivity() {

    override fun getLayout(): Int {
       return R.layout.activity_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SetToolbar
        setToolbar("Register")
        btnRegister.setOnClickListener() {


            val first_name = etFirstName.text.toString()
            val last_name = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirm_password = etConformPassword.text.toString()
            val gender = tvGender.text.toString()
            val phone_no = Integer.parseInt(etPhoneNo.text.toString())

            ApiManger.create()
                .userRegister(first_name,last_name,email,password,confirm_password,gender,phone_no)
                .enqueue(object : Callback<LoginRes>{
                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity,"fail",Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                       Toast.makeText(this@RegisterActivity,"successful",Toast.LENGTH_LONG)

                        val intent = Intent(this@RegisterActivity, HomeScreenActivity::class.java)
                        startActivity(intent)
                    }
                })
        }

    }
}
