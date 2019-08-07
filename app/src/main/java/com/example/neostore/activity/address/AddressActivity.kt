package com.example.neostore.activity.address

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.neostore.activity.address_list.AddressListActivity
import com.example.neostore.R
import com.example.neostore.activity.address.database.AddressDB
import com.example.neostore.activity.address.database.AddressEntity
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.toolbar.*

class AddressActivity : BaseActivity() {

    lateinit var address: String
    lateinit var landmark:String
    lateinit var city: String
    lateinit var state: String
    lateinit var zipcode: String
    lateinit var country: String
    lateinit var db: AddressDB

    override fun getLayout(): Int {
             return  R.layout.activity_address
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        setToolbar("Add Address")
        // Use For Back Buton
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        db = AddressDB.getInstance(this)

        btnSaveAddress.setOnClickListener {
            address = edAddress.text.toString()
            landmark = etLandmark.text.toString()
            city = etCity.text.toString()
            state = etState.text.toString()
            zipcode = etZipCode.text.toString()
            country = etCountry.text.toString()

            var add = AddressEntity(
                0, address, landmark, city, state, zipcode, country
            )
            val thread  = Thread {


                db.addressDao().saveAddress(add)
            }
            thread.start()

            val intent = Intent(this, AddressListActivity::class.java)
            startActivity(intent)
        }
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
