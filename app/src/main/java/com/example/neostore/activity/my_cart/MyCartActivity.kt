package com.example.neostore.activity.my_cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.activity.edit_profile.EditViewModel
import com.example.neostore.activity.my_account.MyAccountResponse
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.activity_my_cart.recyclerView
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*

class MyCartActivity : BaseActivity(){

    lateinit var viewModel: CartViewModel
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var myAdapter: CartAdapter

    override fun getLayout(): Int {
        return R.layout.activity_my_cart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)
        setToolbar("My Cart")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        viewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        viewModel.getDetail(sharedPreferences.getString(AccessToken,""))

        viewModel.cartlistResponse().observe(this,Observer<CartResponse>{
            if(it!=null)
            {
                setAdapter(it)
                getCartDetail(it)
            }
            else
            {
                show("Error")
            }
        })
    }

    @SuppressLint("WrongConstant")
    fun setAdapter(resCart:CartResponse){
        myAdapter = CartAdapter(resCart.data,this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        recyclerView.adapter = myAdapter
    }

    fun getCartDetail(response:CartResponse){
        val cost = response.total
        totalAmount.text = cost.toString()
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
