package com.example.qrgenerator

import android.graphics.Bitmap
import android.net.Uri
import android.renderscript.Sampler
import android.view.View
import android.widget.EditText
import java.util.*

class ItemsViewModel(val id:Int = getAutoId(),val image: String, val text: String , val text_hint: String  ) {

 companion object{
  fun getAutoId(): Int {
   val random = Random()
   return random.nextInt(100)

  }

 }


}

