package com.example.neostore

interface BaseView {
    fun showError(message:String)

    fun showLoading()

    fun hideLoading()

    fun logout()
}