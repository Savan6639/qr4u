package com.example.qrgenerator

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import java.util.*

data class ScanViewModel(val id:Int = getAutoId() , val Image: Int, val text: String , val text_hint: String){

    companion object{
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(200)

        }

    }
}
