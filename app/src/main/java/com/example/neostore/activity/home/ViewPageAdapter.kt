package com.example.neostore.activity.home

import android.content.Context
//import androidx.core.view.PagerAdapter
//import androidx.core.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.neostore.R

class ViewPageAdapter(val context:Context): PagerAdapter() {
    var layoutInflater:LayoutInflater?= null
    var custom_position : Int = 0

    var images = arrayOf(
        R.drawable.slider_img1, R.drawable.slider_img2,
        R.drawable.slider_img3, R.drawable.slider_img4
    )


    override fun isViewFromObject(view: View,`object`: Any): Boolean {
         return view === `object`
    }

    override fun getCount(): Int {
        return images.size      // 1-this is for constant images
        //return Integer.MAX_VALUE  //1-return  infinit slide

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

       // if(custom_position > 3)    //2- make slide infinit
          //  custom_position = 0 //3- make slide infinit

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.activity_view_pager,null)
        val image = v.findViewById<View>(R.id.imageView) as ImageView

        image.setImageResource(images[position])  // 2-this is for constant images
       // image.setImageResource(images[custom_position]) //4- make slide infinit
        //custom_position++                                //5- make slide infinit

        val vp = container as ViewPager
        vp.addView(v,0)


        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }


}