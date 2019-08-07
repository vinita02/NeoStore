package com.example.neostore.base


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.neostore.base.BasePresenter
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar.tvTitle


abstract class BaseActivity : AppCompatActivity() {
    abstract  fun getLayout():Int

   // lateinit var  getPresenter : BasePresenter

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    fun setToolbar(title : String) {
        setSupportActionBar(toolbar)
       // this line use for to remove NeoStore name to overriden on toolbar
        supportActionBar?.title = null
       tvTitle.setText(title)
        // Use For Back Buton
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
        //getPresenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        //getPresenter.onDestroy()
    }

}
