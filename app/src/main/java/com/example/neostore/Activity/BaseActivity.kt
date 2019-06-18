package com.example.neostore.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.neostore.BasePresenter
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

abstract class BaseActivity : AppCompatActivity() {
    abstract  fun getLayout():Int

    abstract val getPresenter : BasePresenter

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    fun setToolbar(title : String) {
        setSupportActionBar(toolbar)
        tvTitle.setText(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    open fun show(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        getPresenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter.onDestroy()
    }

}
