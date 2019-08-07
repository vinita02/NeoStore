package com.example.neostore.activity.address_list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.activity.address.database.AddressDB
import com.example.neostore.activity.edit_profile.EditProfileResponse
import com.example.neostore.activity.edit_profile.EditViewModel
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.toolbar.*

class AddressListActivity : BaseActivity(),AddressListAdapter.AddressListener {


    lateinit var mdb: AddressDB
    lateinit var madapter: AddressListAdapter
    lateinit var add:String
    lateinit var token:String
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var viewModel:AddressListViewModel

    override fun getLayout(): Int {
        return R.layout.activity_address_list
    }


    override fun applyAddress(access_token: String,address: String) {

        token = access_token
        add = address
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        setToolbar("Address List")

        // Use For Back Buton
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        token = sharedPreferences.getString(AccessToken,"")

        viewModel = ViewModelProviders.of(this).get(AddressListViewModel::class.java)

        madapter = AddressListAdapter(this@AddressListActivity, mutableListOf(),this,token)
        add_recycle_view.adapter = madapter

        val thread = Thread {

            mdb = AddressDB.getInstance(this)
            val address=  mdb.addressDao().getAddress()
            runOnUiThread {
                // Stuff that updates the UI
                madapter.addAll(address)
            }
        }
        thread.start()

        btn_placeOrder.setOnClickListener {

             viewModel.setAddress( token,add)

            viewModel.addresslistResponse().observe(this,Observer<AddressListResponse>{
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
    }

             fun response(res: AddressListResponse){

                   show("successfully Edited"+res.message)
             }

}
