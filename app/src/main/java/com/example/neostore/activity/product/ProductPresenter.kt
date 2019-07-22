package com.example.neostore.activity.product

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductPresenter(view: ProductContract.View, context: Context):
    ProductContract.Presenter {


    var mView : ProductContract.View? = null
    var context: Context? = null

    init {
        mView = view
        this.context = context
    }


    override fun productList(product_category_id: String, limit: String, page: String) {

        val apiClient =  ApiManger.create().productList(product_category_id,limit,page)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {

                    if (it != null) {
                   mView?.setAdapter(it)

                    }
                },
                onError = {
                    Log.d("TAG","message:"+it.message)
                    mView?.getProduct("category doesn't Exist")
                    Toast.makeText(context,"this activity"+it.message,Toast.LENGTH_LONG).show()
                },
                onComplete = {
                    mView?.getProduct("Category Exist")
                }
            )
    }

    override fun onStart() {
    }

    override fun onDestroy() {
    }

}