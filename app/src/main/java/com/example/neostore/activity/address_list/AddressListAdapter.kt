package com.example.neostore.activity.address_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.neostore.R
import com.example.neostore.activity.address.database.AddressEntity
import kotlinx.android.synthetic.main.add_row.view.*

class AddressListAdapter ( val mContext: Context, val mData: MutableList<AddressEntity>,lists:AddressListener,
                           token:String): BaseAdapter() {

    lateinit var listener: AddressListener
    lateinit var access_token: String
    lateinit var address: String

    init {
        listener = lists
        access_token = token
    }

    fun addAll(address: List<AddressEntity>) {
        mData.clear()
        mData.addAll(address)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val vieHolder: ViewHolder
        if(view != null) {
            vieHolder = view.tag as ViewHolder
        }else{
            view = LayoutInflater.from(mContext)
                .inflate(R.layout.add_row,parent,false)
            vieHolder = ViewHolder(view)
            view.tag = vieHolder
            address = mData[position].address
            vieHolder.title.text = String.format("%s", address)
        }

        vieHolder.title.text = String.format("%s", address)

        vieHolder.linear.setOnClickListener {
            listener.applyAddress(access_token,address)
        }

        return view!!
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    internal class ViewHolder(view: View) {

        var title: TextView = view.tv_address

        var linear :LinearLayout = view.idLayout
    }

    interface AddressListener{
        fun applyAddress(access_token:String,address:String)
    }
}