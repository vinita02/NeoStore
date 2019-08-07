package com.example.neostore.activity.edit_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.api.ApiManger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

open class EditViewModel:ViewModel() {

    val editProfile:MutableLiveData<EditProfileResponse> = MutableLiveData()
    fun editResponse():MutableLiveData<EditProfileResponse> = editProfile

    fun setDetails(token:String,first_name:String,last_name:String,email: String,
                   dob: String,phone_no: String,profile_pic:String?) {

        val apiClient =  ApiManger.create().editProfiles(token,first_name,last_name,
            email,dob,phone_no,profile_pic)
        apiClient.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy (
                onNext = {
                    if(it!=null)
                    {
                        editProfile.postValue(it)
                    }
                },
                onError = {
                    editProfile.postValue(null)
                },
                onComplete = {
                }
            )

    }

}