package com.example.neostore.activity.my_cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.AccessToken
import com.example.neostore.R
import com.example.neostore.activity.address.AddressActivity
import com.example.neostore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.activity_my_cart.recyclerView
import kotlinx.android.synthetic.main.toolbar.*

class MyCartActivity : BaseActivity(){

    lateinit var viewModel: CartViewModel
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var myAdapter: CartAdapter
    lateinit var deletIcon:Drawable

    private var swipeBackground:ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))

    override fun getLayout(): Int {
        return R.layout.activity_my_cart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        setToolbar("My Cart")

        // This line use for to hid menu button from activity
        ivMenu.setVisibility(View.GONE)

        deletIcon = ContextCompat.getDrawable(this,R.drawable.ic_delete_black_24dp)!!

        viewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        viewModel.getDetail(sharedPreferences.getString(AccessToken,""))

        viewModel.cartlistResponse().observe(this,Observer<CartResponse>{
            if(it!=null)
            {
                setAdapter(it)
                getCartDetail(it)
            }
            else
            {
                show("Error")
            }
        })

        btnOrderNow.setOnClickListener {
            val intent = Intent(this@MyCartActivity,AddressActivity::class.java)
            startActivity(intent)
        }
        enableSwipe()

    }

    fun enableSwipe(){

        val itemTouchHelperCallback = object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position : Int) {
                myAdapter.removeItem(viewHolder)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView

                val iconMargin = (itemView.height - deletIcon.intrinsicHeight)/2
                if(dX > 0){
                    swipeBackground.setBounds(itemView.left,itemView.top,dX.toInt(),itemView.bottom)
                    deletIcon.setBounds(itemView.left+iconMargin,itemView.top+iconMargin,
                        itemView.left+iconMargin+deletIcon.intrinsicWidth,
                        itemView.bottom-iconMargin)

                }else{

                    swipeBackground.setBounds(itemView.right+dX.toInt(),itemView.top,itemView.right,itemView.bottom)
                    deletIcon.setBounds(itemView.right-iconMargin-deletIcon.intrinsicWidth,
                        itemView.top+iconMargin,itemView.right-iconMargin,
                        itemView.bottom-iconMargin)
                }
                      swipeBackground.draw(c)

                c.save()

                if(dX > 0){
                    c.clipRect(itemView.left,itemView.top,dX.toInt(),itemView.bottom)

                }else
                {
                   c.clipRect(itemView.right+dX.toInt(),itemView.top,itemView.right,itemView.bottom)
                }

                c.restore()

                deletIcon.draw(c)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


    }

    @SuppressLint("WrongConstant")
    fun setAdapter(resCart:CartResponse){
        myAdapter = CartAdapter(resCart.data,this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        recyclerView.adapter = myAdapter
    }

    fun getCartDetail(response:CartResponse){
        val cost = response.total
        totalAmount.text = cost.toString()
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
