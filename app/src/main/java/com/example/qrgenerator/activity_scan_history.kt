package com.example.qrgenerator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qrgenerator.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class activity_scan_history : AppCompatActivity() {

    private lateinit var scanAdapter: ScanAdapter
    private lateinit var recyclerView_scan: RecyclerView
    lateinit var button_scan : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_history)

        supportActionBar?.hide();


        button_scan = findViewById(R.id.back_scan)

        button_scan.setOnClickListener{
            finish()
        }
        //      set ID
        recyclerView_scan = findViewById(R.id.recyclerview_scan)
//      set RecyclerView Adapter
        recyclerView_scan.layoutManager = LinearLayoutManager(this)
        recyclerView_scan.itemAnimator = DefaultItemAnimator()

        //      Retrive data From Database()
        val db = DBHelper(this, null)
        val History_List = db.getHistory()

        Log.e("","history" + History_List)
        //      set Adapter
        scanAdapter = ScanAdapter(this, History_List)
        recyclerView_scan.setAdapter(scanAdapter);
        recyclerView_scan.adapter = scanAdapter

        scanAdapter.setOnClickDeleteItem {
            deletehistory(it.id)
            Log.e("","id = " + it.id)
        }
//        {
//            val intent = Intent(this , Itemdetails_qrscan::class.java)
////            intent.putExtra("qr_Scanhistory" , qrvalue)
//            startActivity(intent)
//        }
    }
    fun deletehistory(id: Int) {

        val db = DBHelper(this, null)
        db.deleteHistoryById(id)
        db.getHistory()
    }
}

//val gson = Gson()
//val json = sharedPreferences.getString("Scan_value","")
//Log.e("","jason value == "+ json)
//val type: Type = object : TypeToken<MutableList<ScanViewModel?>?>() {}.type
//List_scan = gson.fromJson(json, type)
//Log.e("","listscan value == "+ List_scan)





