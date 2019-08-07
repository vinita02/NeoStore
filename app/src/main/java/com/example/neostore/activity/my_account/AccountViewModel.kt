package com.example.neostore.activity.my_account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.activity.my_account.MyAccountResponse
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

open class AccountViewModel:ViewModel() {

    val detailOfAccount:MutableLiveData<MyAccountResponse> = MutableLiveData()
    fun accountResponse():MutableLiveData<MyAccountResponse> = detailOfAccount

    fun getDetails(token:String){
       val apiClient =  ApiManger.create().accountDetails(token)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
               onNext = {
                   if(it!=null)
                   {
                       detailOfAccount.postValue(it)
                   }
               },
                onError = {
                    detailOfAccount.postValue(null)
                },
                onComplete = {
                }
            )
   }
}