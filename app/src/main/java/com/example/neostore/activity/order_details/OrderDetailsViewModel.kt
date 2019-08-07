package com.example.neostore.activity.order_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class OrderDetailsViewModel:ViewModel() {
    val orderDetail : MutableLiveData<OrderDetailsResponse> = MutableLiveData()
    fun orderDetailResponse(): MutableLiveData<OrderDetailsResponse> = orderDetail

    fun getDetail(access_token:String,order_id:String) {
        val apiClient =  ApiManger.create().displayOrderDetail(access_token,order_id)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        orderDetail.postValue(it)
                    }
                },
                onError = {
                    orderDetail.postValue(null)
                },
                onComplete = {
                }
            )
    }
}