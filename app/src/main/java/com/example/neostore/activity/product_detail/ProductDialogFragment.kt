package com.example.neostore.activity.product_detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.fragment.app.DialogFragment
import com.example.neostore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_quantity.*


class ProductDialogFragment(lister:ExampleListener,id: String,token:String): DialogFragment() {

    lateinit var TitleName:String
    lateinit var Image:String
  lateinit var listener: ExampleListener
    lateinit  var product_id:String
    lateinit var access_token: String

    init {
        listener = lister
        product_id = id
        access_token =token
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.dialog_fragment_quantity, container, false)


        TitleName = arguments?.getString("title")!!
        Image = arguments?.getString("image")!!
       // Log.d("TAG","Title: "+Image)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvHeader.text = TitleName
        Picasso.get().load(Image).into(imageViewDialog)

        buttonSubmit.setOnClickListener({

            val quantity = etDilogEnterOty.text.toString()
            listener.enterQuntity(access_token,product_id,quantity)
            dialog.dismiss()

        })
    }

    interface ExampleListener{
        fun enterQuntity(access_token: String,product_id:String,quantity:String)
    }


}