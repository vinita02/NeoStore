package com.example.neostore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.SupportActionModeWrapper
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
    }
      /* setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar!!.title ="NeoStore"

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.ic_launcher)
        actionBar.setDisplayUseLogoEnabled(true)*/

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu,menu)
            return true
        }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemid)
        {

        }
        return super.onContextItemSelected(item)
    }


}
