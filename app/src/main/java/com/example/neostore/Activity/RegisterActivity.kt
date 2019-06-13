package com.example.neostore.Activity

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.neostore.Api.ApiManger
import com.example.neostore.Model.LoginRes
import com.example.neostore.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etEmail
import kotlinx.android.synthetic.main.activity_register.etPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : BaseActivity() {

         var gebderCheck:String?= null

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        // SetToolbar
        setToolbar("Register")

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            gebderCheck = if( checkedId == R.id.rbMale) "Male" else "female"
        }



        btnRegister.setOnClickListener() {


            val first_name = etFirstName.text.toString()
            val last_name = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirm_password = etConformPassword.text.toString()
            val phone_no = etPhoneNo.text.toString()


             validation(first_name,last_name,email,password,confirm_password,phone_no)
            ApiManger.create()
                .userRegister(first_name,last_name,email,password,confirm_password,gebderCheck.toString(),phone_no.toLong())
                .enqueue(object : Callback<LoginRes>{
                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity,"fail",Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {

                        if(response.body() != null)
                        {

                            show("Successful")
                        }
                        else
                        {
                            show("Fail")

                        }
                    }
                })
        }

    }

    open fun validation(
        first_name: String,
        last_name: String,
        email: String,
        password: String,
        confirm_password: String,
        phone_no: String
    ):Boolean
    {
        if(first_name.isEmpty()||first_name.length>=32)
        {
            etFirstName.error = "First Name Required"
            etFirstName.requestFocus()
            return false
        }
        if(last_name.isEmpty()||last_name.length>=32)
        {
            etLastName.error = "Last Name Required"
            etLastName.requestFocus()
            return false
        }
        if (email.isEmpty())
        {
            etEmail.error = "Email Required"
            etEmail.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            etPassword.error = "Password Required"
            etPassword.requestFocus()
            return false
        }
        if (confirm_password.isEmpty()) {
            etConformPassword.error = "Password Required"
            etConformPassword.requestFocus()
            return false
        }
        if(phone_no.isEmpty())
        {
            etPhoneNo.error = "Enter valid Number"
            etPhoneNo.requestFocus()
            return false
        }
           return true
    }
}
