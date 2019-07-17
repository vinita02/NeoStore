package com.example.neostore.activity.product

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import com.example.neostore.base.BasePresenter
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_seater_dining_table.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductListActivity : BaseActivity(), ProductContract.View {

    override fun getLayout(): Int {
        return R.layout.activity_product_list
    }

    val presenter = ProductPresenter(this, this)


    override fun getProduct(message: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("Tables")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        presenter.productList(intent.extras?.get("product_id").toString(),"10","1")
        recyclerView.layoutManager = LinearLayoutManager(this)
        presenter.setAdapter(recyclerView)
    }

    override fun showError(message: String) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun logout() {
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
