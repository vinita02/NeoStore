package com.example.neostore.activity.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.neostore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_quantity.imageViewDialog
import kotlinx.android.synthetic.main.dialog_fragment_quantity.tvHeader
import kotlinx.android.synthetic.main.dialog_fragment_rating.*
import com.example.neostore.activity.product_detail.RatingDialogFragment.ExampleDialogListener as RatingDialogFragmentExampleDialogListener

class RatingDialogFragment(lists: ExampleDialogListener, id : String):DialogFragment() {
    lateinit var titleName:String
    lateinit var Image:String
    lateinit var ratingbar:String
    lateinit  var listener : ExampleDialogListener
    lateinit  var product_id: String

    init {
        listener = lists
        product_id = id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.dialog_fragment_rating, container, false)

        titleName = arguments?.getString("title")!!
        Image = arguments?.getString("image")!!
        ratingbar = arguments?.getString("rating")!!
        // Log.d("TAG","Title: "+Image)
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvHeader.text = titleName
        Picasso.get().load(Image).into(imageViewDialog)
        rating_bar.rating = ratingbar.toFloat()

        buttonRateNow.setOnClickListener(View.OnClickListener {

            val rating = rating_bar.rating.toInt()
            listener.applyRating(rating, product_id)
            dialog.dismiss()
        })
    }

    interface ExampleDialogListener{
        fun applyRating(ratting :Int ,product_id:String)

    }
}