package com.example.neostore.activity.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.neostore.base.BasePresenter
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import com.example.neostore.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etPassword
import kotlinx.android.synthetic.main.toolbar.*
//import android.arch.lifecycle.Observer as LifecycleObserver
import kotlinx.android.synthetic.main.activity_register.etEmail as etEmail1

class RegisterActivity : BaseActivity(), RegisterContract.View {

    val presenter = RegisterPresenter(this)

    override val getPresenter: BasePresenter
        get() = presenter

    var gebderCheck:String?= null

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun loginSuccess() {
        show("Successful")
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun loginFail() {
        show("Fail Login")
    }

    override fun showEmailError() {
        etEmail.error = "Email Required"
        etEmail.requestFocus()
    }

    override fun showPasswordError() {
        etPassword.error = "Password Required"
        etPassword.requestFocus()
    }

    override fun showFirstNameError() {
        etFirstName.error="Enter First Name"
        etFirstName.requestFocus()
    }

    override fun showLastNameError() {
        etLastName.error="Enter Last Name"
        etLastName.requestFocus()
    }

    override fun showConformPasswordError() {
        etConformPassword.error="Enter Conform Password"
        etConformPassword.requestFocus()
    }

    override fun showPhoneNoError() {
        etPhoneNo.error="enter Phone Number"
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
        setContentView(getLayout())

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        // SetToolbar Title
        setToolbar("Register")

        // Use For Back Buton
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
         supportActionBar?.setDisplayShowHomeEnabled(true)

        // validation for radio group
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

         val checkRegister =  presenter.validation(first_name,last_name,email,password,
             confirm_password,phone_no)

            if(checkRegister) {
                presenter.getResult(first_name,last_name,email,password,confirm_password, this.gebderCheck!!,phone_no)
            }




             //validation(first_name,last_name,email,password,confirm_password,phone_no)

            /*ApiManger.create()
                .userRegister(first_name,last_name,email,password,confirm_password,gebderCheck.toString(),phone_no.toLong())
                .enqueue(object : Callback<LoginRes>{
                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity,"fail",Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginRes>, response: ProductResponse<LoginRes>) {

                        if(response.body() != null)
                        {

                            show("Successful")
                        }
                        else
                        {
                            show("Fail")

                        }
                    }
                })*/
        }

    }

   /* open fun validation(
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
    }*/
}
