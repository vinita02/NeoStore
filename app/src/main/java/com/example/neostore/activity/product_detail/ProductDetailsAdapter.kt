package com.example.neostore.activity.product_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.squareup.picasso.Picasso

class ProductDetailsAdapter:RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder> {

    private var data :List<ProductImage>?= null
    private var context :Context? = null
    private  var listener:onItemClick? = null

    constructor(data1:List<ProductImage>, context: Context?, liste:onItemClick)
    {
        this.data = data1
        this.context = context
        listener = liste;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setOnClickListener {
            listener?.onClicked(position)
        }
        Picasso.get().load(data!!.get(position)?.image!!).into(holder.imageView)
    }

    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){

         val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }
    interface onItemClick{

        fun onClicked(position: Int)

    }
}