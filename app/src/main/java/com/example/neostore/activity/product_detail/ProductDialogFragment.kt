package com.example.neostore.activity.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.neostore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_quantity.*


class ProductDialogFragment: DialogFragment() {

    lateinit var TitleName:String
    lateinit var Image:String

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

    }


}