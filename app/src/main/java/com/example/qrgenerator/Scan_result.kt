package com.example.qrgenerator

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Scan_result : AppCompatActivity() {

//    private lateinit var scanAdapter: ScanAdapter
//    private var List_scan  = mutableListOf<ScanViewModel>()
    lateinit var textView: TextView
    lateinit var share_result : LinearLayout
    lateinit var back_button : ImageView
    lateinit var web_result : LinearLayout
    lateinit var linearLayout_web: LinearLayout
    lateinit var linearLayout_share: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_result)

        share_result = findViewById(R.id.share_scan)
        web_result = findViewById(R.id.web_result)
        back_button = findViewById(R.id.back_button)
      linearLayout_web = findViewById(R.id.withweb)
      linearLayout_share = findViewById(R.id.withoutweb)
//        button_home = findViewById(R.id.button_home)


        back_button.setOnClickListener{
            finish()
        }

//        button_home.setOnClickListener{
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            intent.putExtra("FRAGMENT_ID", 1)
//            startActivity(intent);
//        }

        supportActionBar?.hide();
////      Set Adapter
//        scanAdapter = ScanAdapter(this, List_scan)
//      get Data
        val result = intent.getStringExtra("qr_scan").toString()
        val text_hint = intent.getStringExtra("" +
                "hint").toString()

//      Show Generated History
        textView = findViewById(R.id.TextMultiLine_result)
        textView.apply {
            text = result
        }

        val db = DBHelper(this, null)

        val Scan = ScanViewModel(Image = R.drawable.text , text = result , text_hint = text_hint)
        db.scanhistory(Scan)
        Toast.makeText(this, result + " added to History", Toast.LENGTH_LONG).show()

        if (URLUtil.isNetworkUrl(result)) {
            share_result = findViewById(R.id.share_scan)
          linearLayout_web.setVisibility(View.VISIBLE)
          linearLayout_share.setVisibility(View.GONE)
        }else{
            share_result = findViewById(R.id.share_layout)
            linearLayout_share.setVisibility(View.VISIBLE)
            linearLayout_web.setVisibility(View.GONE)
        }
        share_result.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, result)
            sendIntent.type = "text/plain"
            Intent.createChooser(sendIntent, "Share via")
            startActivity(sendIntent)
        }

        web_result.setOnClickListener {
            val uri: Uri =
                Uri.parse( result) // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
    fun String.isValidUrl(): Boolean = Patterns.WEB_URL.matcher(this).matches()
}