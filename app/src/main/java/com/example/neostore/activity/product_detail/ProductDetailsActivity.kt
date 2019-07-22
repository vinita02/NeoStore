package com.example.neostore.activity.product_detail

import android.content.Context
import android.os.Bundle
import android.service.quicksettings.Tile
import android.util.EventLogTags
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activity.product.ProductAdapter
import com.example.neostore.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.View, ProductDetailsAdapter.onItemClick {

    private var myadapter:ProductDetailsAdapter? = null
    var context: Context? = null
    private var list:List<ProductImage>? = null

    lateinit var name:String
    lateinit var producer:String
    lateinit var rating:String
    lateinit var description :String
     var cost:Int? = null

    var title : TextView?= null
    var subtitle:TextView? = null
    var ratings : RatingBar? = null
    var descriptions:TextView? = null
    var costs:TextView? = null


    override fun getLayout(): Int {
       return R.layout.activity_product_details
    }

    override fun getProductData(response: SingleDataItem) {
        Log.d("TAG","response")
        name = response.data.name
        producer = response.data.producer
        rating = response.data.rating.toString()
        description = response.data.description
        cost = response.data.cost
        title?.text = name
        subtitle?.text = producer
        ratings?.rating = rating.toFloat()
        descriptions?.text = description
        costs?.text = cost.toString()

    }

    override fun onClicked(position: Int) {
        Picasso.get().load(list?.get(position)?.image).into(imageView)
    }

    override fun getProductImages(productImages: List<ProductImage>) {
        list = productImages;
        Picasso.get().load(productImages.get(0).image).into(imageView)

    }

    override fun setAdapter(data1: List<ProductImage>) {

        myadapter = ProductDetailsAdapter(data1, context, this)
        recyclerView.adapter = myadapter
    }

    val presenter = ProductDetailsPresenter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        setToolbar("6 Seater Dining Table")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        title = findViewById<TextView>(R.id.tvTitles)
        subtitle = findViewById(R.id.tvSmail)
        ratings = findViewById(R.id.ratBar)
        descriptions = findViewById(R.id.tvDescrib)
        costs = findViewById(R.id.tvPrice)


        presenter.productDetails(intent.extras.get("id_value").toString())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)


    }

    override fun getItemProductDetail(message: String) {
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
