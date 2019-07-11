package com.example.neostore.activity.home


//import com.viewpagerindicator.CirclePageIndicator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
//import androidx.core.view.ViewPager
import android.view.*
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.neostore.R
import com.example.neostore.base.BaseActivity
import com.example.neostore.activity.product.ProductListActivity
import com.example.neostore.base.BasePresenter
import com.example.neostore.activity.login.LoginContract
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import java.lang.Override as Override1


class HomeScreenActivity : BaseActivity(), LoginContract.View {

    var current_position: Int = 0

    val presenter = LoginPresenter(this)
    override val getPresenter: BasePresenter
        get() = presenter

    override fun getLayout(): Int {
        return R.layout.activity_home_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////////////////  This is use to Set Toolbar,Add Back Button and Hide Menu button
        setToolbar("NeoSTORE")
        // Use For Back Buton
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        // This is use to open navigation drawer
        ivMenu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        ///////intent////////////////////////
        ivTables.setOnClickListener {
            decide("1")
        }
        ivSofa.setOnClickListener {
            decide("2")
        }
        ivChairs.setOnClickListener {
            decide("3")
        }
        ivCupbords.setOnClickListener {
            decide("4")
        }
        // call init method
        init()
    }

    fun decide(productId:String){
        val bundle=Bundle()
        bundle.putString("product_id",productId)
        val intent = Intent(this@HomeScreenActivity, ProductListActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    ///////// This code are use for Serach icon //////////////////////////
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

    override fun loginSuccess(response: LoginRes?) {

    }

    override fun loginFail() {
    }

    override fun showEmailError() {
    }

    override fun showPasswordError() {
    }

    override fun showError(message: String) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun logout() {
    }

    fun init() {
        //getPresenter.onStart()
        // Adapter code
        val adapter = ViewPageAdapter(this)
        viewPager.adapter=adapter

        indicator.setViewPager(viewPager)

        // For accessing images array in activity
        val img = adapter.images
        val viewpager = findViewById(R.id.viewPager) as ViewPager
        viewpager.adapter = adapter

       /////// Auto start of viewpager//////////////////
        val handler= Handler()
        val update= Runnable {
            if (current_position==img.size){
                current_position=0
            }
            viewpager.setCurrentItem(current_position++,true)
        }
        val swipeTimer= Timer()
        swipeTimer.schedule(object: TimerTask()
        {
            override fun run() {
                handler.post(update)
            }
        },1000,1000)

        indicator?.setOnPageChangeListener(object :ViewPager.OnPageChangeListener
        {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                current_position = p0
            }
        })

    }


}
