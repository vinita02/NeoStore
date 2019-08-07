package com.example.neostore.activity.orderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class OrderListViewModel:ViewModel() {

    val orderlist:MutableLiveData<OrderlistResponse> = MutableLiveData()
    fun orderResponse(): MutableLiveData<OrderlistResponse> = orderlist

    fun orderList(access_token:String)
    {
        val apiClient =  ApiManger.create().displayListOfOrder(access_token)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        orderlist.postValue(it)
                    }
                },
                onError = {
                    orderlist.postValue(null)
                },
                onComplete = {
                }
            )
    }
}