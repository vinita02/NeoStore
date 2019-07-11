package com.example.neostore.base

interface BaseView {
    fun showError(message:String)

    fun showLoading()

    fun hideLoading()

    fun logout()
}