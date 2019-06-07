package com.example.neostore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.SupportActionModeWrapper
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : BaseActivity(){

    override fun getLayout():Int {
        return R.layout.activity_home_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar("Home Screen")
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater : MenuInflater = menuInflater
            inflater.inflate(R.menu.menu,menu)
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId)
        {
            R.id.chair->{
          Toast.makeText(this,"Item 1st selected",Toast.LENGTH_SHORT).show()
                return super.onContextItemSelected(item)
        }

        }

        return super.onContextItemSelected(item)
    }



}
