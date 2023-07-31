package com.example.qrgenerator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import com.example.qrgenerator.R
import com.example.qrgenerator.ui.profile.ProfileFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Type


class ActivityItemDetails : AppCompatActivity() {

    lateinit var share_create : LinearLayout
    private var qrList = mutableListOf<ItemsViewModel>()
    lateinit var button_item : ImageView
    lateinit var button_home : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)



        supportActionBar?.hide()
        button_item = findViewById(R.id.back_itemdetail)
        button_home = findViewById(R.id.button_home)
        share_create = findViewById(R.id.share_create)

        button_item.setOnClickListener{
            finish()
        }

        button_home.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("FRAGMENT_ID", 1)
            startActivity(intent);
        }

        val qrvalue = intent.getStringExtra("qr_image")
        val imageBytes = Base64.decode(qrvalue , Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        val imageView: ImageView = findViewById(R.id.imageView_qr)
        val messageImageView: ImageView = findViewById<ImageView>(R.id.imageView_qr).apply {

            imageView.setImageBitmap(decodedImage)
        }


    share_create.setOnClickListener{

        shareImageandText(decodedImage)
    }
    }

    private fun shareImageandText(decodedImage: Bitmap) {

        val uri: Uri = getmageToShare(decodedImage)
        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        intent.setType("image/png");
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    private fun getmageToShare(decodedImage : Bitmap): Uri {
        val imagefolder = File(cacheDir, "images")
        var uri: Uri? = null
        try {
            imagefolder.mkdirs()
            val file = File(imagefolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            decodedImage.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush()
            outputStream.close()
            uri = FileProvider.getUriForFile(this, "com.anni.shareimage.fileprovider", file)
        } catch (e: Exception) {
            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
        }
        return uri!!
    }

}