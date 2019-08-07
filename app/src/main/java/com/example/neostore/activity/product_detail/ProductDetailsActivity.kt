package com.example.neostore.activity.product_detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.View, ProductDetailsAdapter.onItemClick,
    RatingDialogFragment.ExampleDialogListener,ProductDialogFragment.ExampleListener
{
    override fun enterQuntity(access_token: String, product_id: String, quantity: String) {
        presenter.setQuantity(access_token,product_id,quantity)
    }

    override fun getQuantity(data: QuantityResponse){

        Log.d("TAG","Quantity Response")
    }

    override fun applyRating(rating :Int ,product_id: String) {
        presenter.setRating(rating,product_id)
    }

    override fun getRatingData(data: RatingResponse) {
              Log.d("TAG","Rating Response")
        var  rate = (data.data.rating).toFloat()
        ratings?.rating = rate
        Log.d("TAG","Rating Response")
    }

    private var myadapter:ProductDetailsAdapter? = null
    var context: Context? = null
    private var list:List<ProductImage>? = null
    val presenter = ProductDetailsPresenter(this, this)

    lateinit var name:String
    lateinit var producer:String
    lateinit var rating:String
    lateinit var description :String
     var cost:Int? = null
    var Pimage:String? = null

    var title : TextView?? = null
    var subtitle:TextView? = null
    var ratings : RatingBar? = null
    var descriptions:TextView? = null
    var costs:TextView? = null
    lateinit  var product_id : String
    lateinit var token:String
  //  lateinit var Title:String

    val sharedPrefFile = "kotlinsharedpreference"

    override fun getLayout(): Int {
       return R.layout.activity_product_details
    }

    override fun getProductData(response: SingleDataItem) {
       // Log.d("TAG","response")
        name = response.data.name
       //  Title = name
        producer = response.data.producer
        rating = response.data.rating.toString()
        description = response.data.description
        cost = response.data.cost
        response.data.productImages

        title?.text = name
        ////////////////Set Toolbar//////////
        setToolbar(name)

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
        Pimage = productImages.get(0).image
    }

    override fun setAdapter(data1: List<ProductImage>) {
        myadapter = ProductDetailsAdapter(data1, context, this)
        recyclerView.adapter = myadapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        title = findViewById<TextView>(R.id.tvTitles)
        subtitle = findViewById(R.id.tvSmail)
        ratings = findViewById(R.id.ratBar)
        descriptions = findViewById(R.id.tvDescrib)
        costs = findViewById(R.id.tvPrice)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
         token = sharedPreferences.getString(AccessToken,"")
        product_id = intent.extras.getInt("id_value").toString()

        presenter.productDetails(intent.extras.get("id_value").toString())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)

///////////passing data to dialog fragment///////////
        btnBusyNow.setOnClickListener {
            //lLayoutRating.setVisibility(View.GONE)
            val fm = supportFragmentManager
            val dialogFragment = ProductDialogFragment(this,product_id,token)
            val  bundle = Bundle()
            bundle.putString("title",name);
            bundle.putString("image",Pimage)
            dialogFragment.setArguments(bundle)
            dialogFragment.show(fm, "ProductDialogFragment")
        }

        btnRate.setOnClickListener{
           // presenter.setRating(intent.extras?.get("product_id").toString())
           // presenter.setRating("1")
            val fm = supportFragmentManager
            val ratingFragment = RatingDialogFragment(this, product_id)
            val bundle = Bundle()
            bundle.putString("title",name)
            bundle.putString("image",Pimage)
            bundle.putString("rating",rating)
            ratingFragment.setArguments(bundle)
            ratingFragment.show(fm,"RatingDialogFragment")
        }
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
