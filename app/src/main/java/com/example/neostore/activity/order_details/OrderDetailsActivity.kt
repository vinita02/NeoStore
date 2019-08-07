package com.example.neostore.activity.order_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.toolbar.*

class OrderDetailsActivity : BaseActivity() {

    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var viewModel: OrderDetailsViewModel
    lateinit var mAdapter: OrderDetailsAdapter

    override fun getLayout(): Int {
        return R.layout.activity_order_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)


        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(AccessToken,"")

        viewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel::class.java)

        viewModel.getDetail(token,intent.extras.get("order_id").toString())

        viewModel.orderDetailResponse().observe(this, Observer<OrderDetailsResponse>{

            if(it!=null)
            {
                setAdapter(it)
                response(it)
                show("Success")
            }
            else
            {
                show("Error")
            }

        })
    }
    @SuppressLint("WrongConstant")
    fun setAdapter(res: OrderDetailsResponse)
    {
        mAdapter = OrderDetailsAdapter(this,res.data.orderDetails)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        recyclerView.adapter = mAdapter

    }

    fun response(res:OrderDetailsResponse){
        val id = "Order Id : "+res.data.id.toString()
        tvTotalAmount.text = "Rs: "+res.data.cost.toString()
        setToolbar(id)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

}
