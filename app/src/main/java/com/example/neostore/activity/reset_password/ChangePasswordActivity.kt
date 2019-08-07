package com.example.neostore.activity.reset_password

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.activity.edit_profile.EditProfileResponse
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.toolbar.*

class ChangePasswordActivity : BaseActivity() {

    lateinit var viewModel: ResetViewModel
    val sharedPrefFile = "kotlinsharedpreference"

    override fun getLayout(): Int {
        return R.layout.activity_change_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        setToolbar("Reset Password")

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)
        viewModel = ViewModelProviders.of(this).get(ResetViewModel::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        btn_reset.setOnClickListener {
            val currentPassword = et_cureentPass.text.toString()
            val newPassword = et_newPass.text.toString()
            val confirmPassword = et_confirmPass.text.toString()

            viewModel.setPassword(sharedPreferences.getString(AccessToken,""),currentPassword
            ,newPassword,confirmPassword)
        }
        viewModel.editPassResponse().observe(this, Observer<ResetResponse>{

            if(it!=null)
            {
                response(it)
            }
            else
            {
                show("Error")
            }
        })

    }
    fun response(res:ResetResponse){
        show("Password successfully changed"+res.message)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.chair -> {
                Toast.makeText(this, "Item 1st selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}
