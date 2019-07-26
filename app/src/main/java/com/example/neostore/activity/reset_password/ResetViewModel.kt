package com.example.neostore.activity.reset_password

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ResetViewModel:ViewModel() {

    val editPassword : MutableLiveData<ResetResponse> = MutableLiveData()

    fun editPassResponse():MutableLiveData<ResetResponse> = editPassword

    fun setPassword(token: String,old_password: String,password: String,confirm_password: String)
    {
        val apiClient =  ApiManger.create().changePassword(token,old_password,password,confirm_password)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        editPassword.postValue(it)

                    }

                },
                onError = {
                    editPassword.postValue(null)

                },
                onComplete = {

                }
            )
    }

}