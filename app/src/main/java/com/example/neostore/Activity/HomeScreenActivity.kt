package com.example.neostore.Activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.neostore.BasePresenter
import com.example.neostore.Contract.LoginContract
import com.example.neostore.Contract.RegisterContract
import com.example.neostore.Model.LoginRes
import com.example.neostore.Presenter.LoginPresenter
import com.example.neostore.Presenter.RegisterPresenter
import com.example.neostore.R
import com.example.neostore.ViewPageAdapter

class HomeScreenActivity() : BaseActivity(),LoginContract.View{

    lateinit var viewPager: ViewPager

    val presenter = LoginPresenter(this)
    override val getPresenter: BasePresenter
        get() = presenter

    override fun getLayout():Int {
        return R.layout.activity_home_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("Home Screen")
        //getPresenter.onStart()

        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter = ViewPageAdapter(this)
        viewPager.adapter = adapter
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater : MenuInflater = menuInflater
            inflater.inflate(R.menu.menu,menu)
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId)
        {
            R.id.chair ->{
          Toast.makeText(this,"Item 1st selected",Toast.LENGTH_SHORT).show()
                return super.onContextItemSelected(item)
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

}
