package com.example.neostore.activity.product_detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductDetailsPresenter(view: ProductDetailsContract.View, context: Context):
ProductDetailsContract.Presenter{

    var mView: ProductDetailsContract.View? = null

    var context: Context? = null

    init {
        mView = view
        this.context = context
    }

    override fun productDetails(product_id: String) {

        val apiClient =  ApiManger.create().productDetails(product_id)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    if(it != null){
                        mView!!.getProductData(it)
                        mView!!.setAdapter( it.data.productImages)
                        mView!!.getProductImages(it.data.productImages)
                    }
                },
                onError = {
                    Log.d("TAG","message_error:"+it.message)
                    mView?.getItemProductDetail("category doesn't Exist")
                    Toast.makeText(context,"this activity"+it.message, Toast.LENGTH_LONG).show()
                },
                onComplete = {
                    mView?.getItemProductDetail("Category Exist")
                }

            )
    }

    fun setRating(rating : Int,product_id: String){
     val apiClient =  ApiManger.create().productRating(product_id,rating)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    mView!!.getRatingData(it)

                },
                onError = {

                },
                onComplete = {

                })

    }

    fun setQuantity(access_token: String,product_id:String,quantity:String){
        val apiClient = ApiManger.create().enterQuantity(access_token,product_id,quantity)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
               onNext =  {
                   mView!!.getQuantity(it)

               },
                onError = {
                    Log.d("TAG","message_error")

                },
                onComplete = {

                })


    }

    override fun onStart() {
    }
    override fun onDestroy() {
    }
}