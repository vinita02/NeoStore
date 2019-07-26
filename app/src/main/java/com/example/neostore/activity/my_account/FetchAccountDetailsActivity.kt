package com.example.neostore.activity.my_account

import android.content.Context
import android.content.Intent
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
import com.example.neostore.activity.reset_password.ChangePasswordActivity
import com.example.neostore.activity.edit_profile.EditProfileActivity
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fetch_account_details.*
import kotlinx.android.synthetic.main.toolbar.*

class FetchAccountDetailsActivity : BaseActivity() {

    lateinit var viewModel: AccountViewModel
    val sharedPrefFile = "kotlinsharedpreference"

    override fun getLayout(): Int {
        return R.layout.activity_fetch_account_details
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_account_details)

        setToolbar("My Account")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        //val editor:SharedPreferences.Editor = sharedPreferences.edit()

        viewModel.getDetails(sharedPreferences.getString(AccessToken,""))

        viewModel.accountResponse().observe(this,Observer<MyAccountResponse>{

            if(it!=null)
            {
                  response(it)
            }
            else
            {
                show("Error")
            }
        })

        btn_edit_profile.setOnClickListener {
            val intent = Intent(this@FetchAccountDetailsActivity,
                EditProfileActivity::class.java)
            startActivity(intent)
        }

        btn_resetPassword1.setOnClickListener {
            val intent = Intent(this@FetchAccountDetailsActivity,
               ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

  private fun response(res:MyAccountResponse){
      et_firstName.setText(res.data.userData.firstName)
      et_lastName.setText(res.data.userData.lastName)
      et_email.setText(res.data.userData.email)
      et_phn_no.setText(res.data.userData.phoneNo)
      et_dob.setText(res.data.userData.dob.toString())
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
