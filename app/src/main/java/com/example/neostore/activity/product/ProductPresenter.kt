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

   private var myadapter: ProductAdapter? = null
    var mView : ProductContract.View? = null
    lateinit var mRecyclerView: RecyclerView
    var context: Context? = null

    init {
        mView = view
        this.context = context
    }

    fun setAdapter(mRecyclerView: RecyclerView) {
        this.mRecyclerView = mRecyclerView
    }
    override fun productList(product_category_id: String, limit: String, page: String) {

        val apiClient =  ApiManger.create().productList(product_category_id,limit,page)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {

                    if (it != null) {

                        myadapter = ProductAdapter(it.data1, context)

                        mRecyclerView.adapter = myadapter

                        mView?.getProduct(it.data1!![1].name!!)

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