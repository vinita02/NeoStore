package com.example.neostore.activity.home


//import com.viewpagerindicator.CirclePageIndicator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
//import androidx.core.view.ViewPager
import android.view.*
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import com.example.neostore.R
import com.example.neostore.activity.login.LoginActivity
import com.example.neostore.base.BaseActivity
import com.example.neostore.activity.product.ProductListActivity
import com.example.neostore.base.BasePresenter
import com.example.neostore.activity.login.LoginContract
import com.example.neostore.activity.login.model.LoginRes
import com.example.neostore.activity.login.LoginPresenter
import com.example.neostore.activity.my_account.FetchAccountDetailsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import java.lang.Override as Override1


class HomeScreenActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener {

    val sharedPrefFile = "kotlinsharedpreference"


    override fun onNavigationItemSelected(it: MenuItem): Boolean {

        when(it.itemId){
            R.id.logout ->{
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                editor.clear()
                editor.apply()
                val intent1 = Intent(this@HomeScreenActivity, LoginActivity::class.java)
                startActivity(intent1)
                finish()
            }
            R.id.myAccount ->{

                val intent = Intent(this@HomeScreenActivity,FetchAccountDetailsActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    var current_position: Int = 0

    override fun getLayout(): Int {
        return R.layout.activity_home_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////////////////  This is use to Set Toolbar,Add Back Button and Hide Menu button
        setToolbar("NeoSTORE")
        navigationView.setNavigationItemSelectedListener(this)

        // This is use to open navigation drawer
        ivMenu.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        ///////intent////////////////////////
        ivTables.setOnClickListener {
            navigatToProductList("1")
        }
        ivSofa.setOnClickListener {
            navigatToProductList("2")
        }
        ivChairs.setOnClickListener {
            navigatToProductList("3")
        }
        ivCupbords.setOnClickListener {
            navigatToProductList("4")
        }
        // call init method
        init()

    }

    fun navigatToProductList(productId:String){
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
