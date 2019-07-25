package com.example.neostore.activity.product_detail

import com.example.neostore.RatingResponse
import com.example.neostore.base.BasePresenter
import com.example.neostore.base.BaseView

interface ProductDetailsContract {
    interface View: BaseView
    {
        fun getProductData(data: SingleDataItem)
        fun getRatingData(data: RatingResponse)
        fun  getItemProductDetail(message:String)
        fun setAdapter(data1:List<ProductImage>)
        fun getProductImages(productImages: List<ProductImage>)

    }

    interface Presenter: BasePresenter {
        fun productDetails(product_id:String)
    }
}