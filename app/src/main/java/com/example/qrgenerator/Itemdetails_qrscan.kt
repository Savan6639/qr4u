package com.example.qrgenerator


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class Itemdetails_qrscan : AppCompatActivity() {


    lateinit var textView: TextView
    lateinit var share_result : LinearLayout
    lateinit var back_button : ImageView
    lateinit var button_home : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemdetails_qrscan)

        share_result = findViewById(R.id.share_scan)

        back_button = findViewById(R.id.back_button)
        button_home = findViewById(R.id.button_home)


        back_button.setOnClickListener{
        finish()
        }

//        button_delete.setOnClickListener{
//            deletehistory(it.id)
//        }

        button_home.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("FRAGMENT_ID", 1)
            startActivity(intent);
        }

        supportActionBar?.hide();

//      get Data
        val result = intent.getStringExtra("text").toString()



//      Show Generated History
        textView = findViewById(R.id.TextMultiLine_result)

        textView.apply {
            text = result
        }


        share_result.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, result)
            sendIntent.type = "text/plain"
            Intent.createChooser(sendIntent, "Share via")
            startActivity(sendIntent)
        }
    }
//    fun deletehistory(id: Int) {
//
//        val db = DBHelper(this, null)
//        db.deleteHistoryById(id)
//        db.getHistory()
//
//    }
}
