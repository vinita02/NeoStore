package com.example.neostore.activity.address_list


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class AddressListViewModel : ViewModel(){

    val addresslist : MutableLiveData<AddressListResponse> = MutableLiveData()
    fun addresslistResponse():MutableLiveData<AddressListResponse> = addresslist

    fun setAddress(access_token:String,address:String){
        val apiClient =  ApiManger.create().addAddressList(access_token,address)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        addresslist.postValue(it)
                    }
                },
                onError = {
                    addresslist.postValue(null)
                },
                onComplete = {
                }
            )
    }

}