package com.example.neostore.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }
    abstract  fun getLayout():Int

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

}
