package com.example.qrgenerator

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_create_history : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var createAdapter: CreateAdapter
    lateinit var back_create: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_history)

        supportActionBar?.hide()

//        delete = findViewById(R.id.Delete)

//      Click on Back Button
        back_create = findViewById(R.id.back_Create)
        back_create.setOnClickListener {
            finish()
        }
//      set ID
        recyclerView = findViewById(R.id.recyclerview_create)
//      set RecyclerView Adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        val db = DBHelper(this, null)
        val History_List = db.getName()
        Log.e(" ", "rrrr" + History_List.size)


//      set Adapter
        createAdapter = CreateAdapter(this, History_List)
        recyclerView.setAdapter(createAdapter);
        recyclerView.adapter = createAdapter




        createAdapter.setOnClickDeleteItem {
            deletehistory(it.id)
            Log.e("","id = " + it.id)
        }//            val intent = Intent(this , ActivityItemDetails::class.java)
//            intent.putExtra("qr_history" , qrvalue)
//            startActivity(intent)
//        }



    }

    fun deletehistory(id: Int) {

        val db = DBHelper(this, null)
        db.deleteHistoryById(id)
        db.getName()

//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Are You Sure you want to delet item?")
//        builder.setCancelable(true)
//        builder.setPositiveButton("Yes") { dialog, _ ->
//
//            val db = DBHelper(this, null)
//            db.deleteHistoryById(id)
//            db.getName()
//            dialog.dismiss()
//        }
//        builder.setNegativeButton("No") { dialog, _ ->
//            dialog.dismiss()
//        }
//
//        val alert = builder.create()
//        alert.show()
    }
}