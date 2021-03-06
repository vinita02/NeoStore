package com.example.neostore.activity.my_cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CartViewModel:ViewModel() {

    val cartlistitem : MutableLiveData<CartResponse> = MutableLiveData()
    fun cartlistResponse():MutableLiveData<CartResponse> = cartlistitem

    fun getDetail(access_token:String) {
        val apiClient =  ApiManger.create().cartList(access_token)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        cartlistitem.postValue(it)
                    }
                },
                onError = {
                    cartlistitem.postValue(null)
                },
                onComplete = {
                }
            )
    }

    val cartDeletitem : MutableLiveData<DeleteCartResponse> = MutableLiveData()
    fun cartDeletitemResponse() : MutableLiveData<DeleteCartResponse> = cartDeletitem

    fun deleteDetail(access_token: String,product_id:String){
        val apiClient = ApiManger.create().deleteCartDetail(access_token,product_id)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it != null){
                        cartDeletitem.postValue(it)
                    }
                },
                onError = {
                    cartDeletitem.postValue(null)
                },
                onComplete = {

                })

    }
}