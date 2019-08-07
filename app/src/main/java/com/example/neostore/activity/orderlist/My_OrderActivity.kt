package com.example.neostore.activity.orderlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.activity.my_cart.CartAdapter
import com.example.neostore.activity.my_cart.CartResponse
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my__order.*
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.orderlist.*
import kotlinx.android.synthetic.main.orderlist.view.*
import kotlinx.android.synthetic.main.toolbar.*

class My_OrderActivity :BaseActivity() {
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var viewModel: OrderListViewModel
    lateinit var mAdapter: OrderlistAdapter

    override fun getLayout(): Int {
        return R.layout.activity_my__order
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my__order)

        setToolbar("My Orders")

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(AccessToken,"")

        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)

           viewModel.orderList(token)
        viewModel.orderResponse().observe(this,Observer<OrderlistResponse>{

            if(it!=null)
            {
                setAdapter(it)
                show(it.message)
            }
            else
            {
                show("Error")
            }

        })

    }
    @SuppressLint("WrongConstant")
    fun setAdapter(res: OrderlistResponse)
    {
        mAdapter = OrderlistAdapter(this,res.data)
        order_recycle_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        order_recycle_view.adapter = mAdapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


}
