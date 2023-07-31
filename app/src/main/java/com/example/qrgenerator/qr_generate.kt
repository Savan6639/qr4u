package com.example.qrgenerator

import android.R.attr
import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream


class qr_generate : AppCompatActivity() {

    private lateinit var createAdapter: CreateAdapter
    private var List  = ArrayList<ItemsViewModel>()
    private var qrListSave = mutableListOf<ItemsViewModel>()
    private var mergelist_result = mutableListOf<ItemsViewModel>()
    lateinit var share_button : LinearLayout
    lateinit var Edit_button : TextView
    lateinit var dbHelper : DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generate)

        getSupportActionBar()?.hide()

         Edit_button = findViewById(R.id.Edit_button)
        //      set Adapter
        createAdapter = CreateAdapter(this, List)
        val text_name = intent.getStringExtra("msgValue").toString()
        val message = intent.getStringExtra("qrCodeValue").toString()
        val text_hint = intent.getStringExtra("msg_hint").toString()
        val button_save = findViewById<Button>(R.id.button_save)
        share_button = findViewById(R.id.share_button)

        val imageBytes = Base64.decode(message , Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        val imageView: ImageView = findViewById(R.id.imageView)
        val messageImageView: ImageView = findViewById<ImageView>(R.id.imageView).apply {
            imageView.setImageBitmap(decodedImage)
        }

        val db = DBHelper(this, null)

         fun addHistory() {
            val Create = ItemsViewModel(image = message , text = text_name , text_hint = text_hint)
             db.addName(Create)

        }


//       Merge Two List
        fun <T> merge(first: MutableList<T>, second: MutableList<T>): List<T> {
            return first + second
        }

        Edit_button.setOnClickListener {
            finish()
        }


//      Click on Save Button
        button_save.setOnClickListener {

            addHistory()
            Toast.makeText(this, text_hint + " added to History", Toast.LENGTH_LONG).show()


        }




//
//            val sharedPreferences: SharedPreferences = this.getSharedPreferences("sharedPrefrence", Context.MODE_PRIVATE)
//            val gson = Gson()
//            List.add( ItemsViewModel(message ,text_hint , text_name))
//
//            val editor: SharedPreferences.Editor = sharedPreferences.edit()
//
//            try{
//                val json = sharedPreferences.getString("List_key","")
//                Log.e("","value == "+ json)
//                val type: Type = object : TypeToken<MutableList<ItemsViewModel?>?>() {}.type
//                qrListSave = gson.fromJson(json, type)
//                val mergelist: List<ItemsViewModel> = merge(List, qrListSave)
//                mergelist_result = mergelist as MutableList<ItemsViewModel>
//            }
//            catch(e: NullPointerException){
//                mergelist_result = List
//            }
//
//            val jsonput = gson.toJson(mergelist_result)
//            Log.e("","size == "+mergelist_result.size)
//            editor.putString("List_key", jsonput)
//            editor.putString("qr_value" ,message)
//            Log.e("","value == "+jsonput)
//            editor.apply()
//            editor.commit()
//
//            Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();

       share_button.setOnClickListener {

           shareImageandText(decodedImage);
//           val sendIntent = Intent()
//           sendIntent.action = Intent.ACTION_SEND
//           sendIntent.putExtra(Intent.EXTRA_TEXT, message)
//           sendIntent.type = "text/plain"
//           Intent.createChooser(sendIntent, "Share via")
//           startActivity(sendIntent)
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






//        val url = "https://api.whatsapp.com/send?phone="+messageImageView;
//        val Intent  =  Intent(android.content.Intent.ACTION_VIEW);
//        Intent.setData(Uri.parse(url));
//        startActivity(Intent)


//    val file = File(getExternalFilesDir(null),System.currentTimeMillis().toString() + ".png")
//    file.createNewFile()
//    val b = decodedImage
//    FileOutputStream(file).use { out ->
//        b.compress(Bitmap.CompressFormat.PNG, 100, out)
//    }
//
//    val share = Intent(Intent.ACTION_SEND)
//    share.type = "image/jpeg"
//    val photoURI = FileProvider.getUriForFile(this, applicationContext.packageName.toString() + ".provider", file)
//
//    share.putExtra(Intent.EXTRA_STREAM, photoURI)
//    startActivity(Intent.createChooser(share, "Share Image"))
//
//    Toast.makeText(this, "Completed!!", Toast.LENGTH_SHORT).show()
//} catch (e: IOException) {
//    e.printStackTrace()
//    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
//}