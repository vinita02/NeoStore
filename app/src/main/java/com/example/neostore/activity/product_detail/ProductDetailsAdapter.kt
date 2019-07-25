package com.example.neostore.activity.product_detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.RatingDialogFragment
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

     /*  holder.linearLayout.setOnClickListener {
           data!!.get(position).id
           val intent = Intent(context, RatingDialogFragment::class.java)
           intent.putExtra("id_value", data!!.get(position).id)
           context?.startActivity(intent)
       }*/
    }

    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){

         val imageView = itemView.findViewById<ImageView>(R.id.imageView)
//val linearLayout = itemView.findViewById<LinearLayout>(R.id.lLayout)
    }
    interface onItemClick{

        fun onClicked(position: Int)

    }
}