package com.example.neostore.activity.product

import com.example.neostore.base.BasePresenter
import com.example.neostore.base.BaseView

interface ProductContract {
    interface View: BaseView
    {
        fun  getProduct(message:String)
    }

    interface Presenter: BasePresenter {
        fun productList(product_category_id:String,limit:String,page:String)
    }
}