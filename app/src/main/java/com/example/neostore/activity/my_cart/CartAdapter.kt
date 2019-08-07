package com.example.neostore.activity.my_cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activity.product.model.ProductItem
import com.squareup.picasso.Picasso


class CartAdapter(data1: ArrayList<DataX>, context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var data: ArrayList<DataX>? = data1
    private var context:Context? = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_list,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(data!!.get(position).product.productImages).into(holder.imageV)
        holder.textViewTitle.text = data!!.get(position).product.name
        holder.category.text = data!!.get(position).product.productCategory
        holder.textViewPrice.text = data!!.get(position).product.cost.toString()

    }


    fun removeItem(holder:RecyclerView.ViewHolder){

        data?.removeAt(holder.adapterPosition)
        notifyItemRemoved(holder.adapterPosition)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imageV = itemView.findViewById<View>(R.id.imageView) as ImageView
        val textViewTitle = itemView.findViewById<TextView>(R.id.tvTitleHeading)
        val category = itemView.findViewById<TextView>(R.id.tvCategory)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textViewPrice)

    }
}