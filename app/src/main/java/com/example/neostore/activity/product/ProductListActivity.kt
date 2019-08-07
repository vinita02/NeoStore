package com.example.neostore.activity.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activity.product.model.ProductItem
import com.example.neostore.activity.product.model.ProductResponse
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*
import com.example.neostore.activity.product.ProductAdapter as ProductAdapter
import kotlinx.android.synthetic.main.activity_product_list.recyclerView as recyclerView1

class ProductListActivity : BaseActivity(), ProductContract.View {


  lateinit var myadapter: ProductAdapter
    var context: Context? = null

    override fun setAdapter(product: ProductResponse) {
        myadapter = ProductAdapter(product.data1,this)
        recyclerView.adapter = myadapter
    }

    override fun getLayout(): Int {
        return R.layout.activity_product_list
    }

    val presenter = ProductPresenter(this, this)

    override fun getProduct(message: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        presenter.productList(intent.extras?.get("product_id").toString(),"10","1")
        recyclerView.layoutManager = LinearLayoutManager(this)

        when(intent.extras.get("product_id").toString()){
            "1" -> setToolbar("Tables")
            "2" -> setToolbar("Sofas")
            "3" -> setToolbar("Chairs")
            "4" -> setToolbar("Copboards")

        }

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
