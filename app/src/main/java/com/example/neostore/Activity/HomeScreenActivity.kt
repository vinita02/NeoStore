package com.example.neostore.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.neostore.R

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
            R.id.chair ->{
          Toast.makeText(this,"Item 1st selected",Toast.LENGTH_SHORT).show()
                return super.onContextItemSelected(item)
        }

        }

        return super.onContextItemSelected(item)
    }



}
