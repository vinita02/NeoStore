package com.example.neostore.activity.edit_profile

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
import com.example.neostore.*
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class EditProfileActivity : BaseActivity() {

    lateinit var viewModel:EditViewModel
    val sharedPrefFile = "kotlinsharedpreference"

    override fun getLayout(): Int {
          return R.layout.activity_edit_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setToolbar("Edit Profile")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        btn_submit.setOnClickListener {

            val firstName = et_firstName.text.toString()
            val lastName = et_lastName.text.toString()
            val email = et_email.text.toString()
            val dob = et_dob.text.toString()
            val phone_no = et_phn_no.text.toString()

            viewModel.setDetails(sharedPreferences.getString(AccessToken,""),firstName,
                lastName,email,dob,phone_no,"null")
        }

        viewModel.editResponse().observe(this,Observer<EditProfileResponse>{

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

    fun response(res:EditProfileResponse){
        show("successfully Edited"+res.message)

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
