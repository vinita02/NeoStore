package com.example.neostore.activity.order_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.squareup.picasso.Picasso


class OrderDetailsAdapter(mcontext: Context,var data:List<OrderDetail>):RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>() {

    private var context:Context? = null

    init {
        context = mcontext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_detail_list,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(data.get(position).prodImage).into(holder.imageV)

        holder.textViewTitle.text = data.get(position).prodName
        holder.category.text = data.get(position).prodCatName
        holder.quantity.text = data.get(position).quantity.toString()
        holder.textViewPrice.text = data.get(position).total.toString()

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var imageV = itemView.findViewById<View>(R.id.imageView) as ImageView
        val textViewTitle = itemView.findViewById<TextView>(R.id.tvTitleHeading)
        val category = itemView.findViewById<TextView>(R.id.tvCategory)
        val quantity = itemView.findViewById<TextView>(R.id.tvQuantity)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPrice)

    }
}