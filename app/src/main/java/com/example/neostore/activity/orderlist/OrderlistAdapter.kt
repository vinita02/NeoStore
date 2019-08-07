package com.example.neostore.activity.orderlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activity.order_details.OrderDetailsActivity

class OrderlistAdapter(mcontext: Context,var data:List<Data>):RecyclerView.Adapter<OrderlistAdapter.ViewHolder>() {

    private var context:Context? = null

    init {
        context = mcontext
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):ViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.orderlist,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.orderid.text = "Order Id: "+data!![p1].id.toString()
        p0.ordercost.text = "Rs: "+data!![p1].cost.toString()
        p0.orderdate.text = "Ordered Date: "+data!![p1].created

        p0.orderlayout.setOnClickListener {
            data.get(p1).id
            val intent = Intent(context,OrderDetailsActivity::class.java)
            intent.putExtra("order_id",data.get(p1).id)
            context?.startActivity(intent)


        }
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         val orderid = itemView.findViewById<View>(R.id.tv_orderId) as TextView
         val ordercost = itemView.findViewById<View>(R.id.tv_orderCost)as TextView
         val orderdate = itemView.findViewById<View>(R.id.tv_orderDate)as TextView
        val orderlayout = itemView.findViewById<RelativeLayout>(R.id.relativeLayout)as RelativeLayout
    }

}