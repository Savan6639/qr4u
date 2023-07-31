package com.example.qrgenerator

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "QR4YOU.sqlite"

        // below is the variable for database version
        private val DATABASE_VERSION = 2

        // below is the variable for table name
        val TABLE_NAME = "qr_table"
        val TABLE_NAME_SCAN = "qrscan_table"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val IMAGE_COL = "image"

        // below is the variable for age column
        val TEXT_COL = "text"

        // below is the variable for qrcode column
        val TEXTHINT_COL = "text_hint"
    }
    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                IMAGE_COL + " TEXT," +
                TEXT_COL + " TEXT," +
                TEXTHINT_COL + " TEXT" + ")")

        val query_scan = ("CREATE TABLE " + TABLE_NAME_SCAN + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                IMAGE_COL + " TEXT," +
                TEXT_COL + " TEXT," +
                TEXTHINT_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
        db.execSQL(query_scan)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
//    fun addName(name : String, age : String ,qrcode :String){
fun addName( create : ItemsViewModel){
        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(ID_COL,create.id)
        values.put(IMAGE_COL, create.image)
        values.put(TEXT_COL, create.text)
        values.put(TEXTHINT_COL, create.text_hint)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    fun scanhistory( scan : ScanViewModel){
        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(ID_COL,scan.id)
        values.put(IMAGE_COL, scan.Image)
        values.put(TEXT_COL, scan.text)
        values.put(TEXTHINT_COL, scan.text_hint)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME_SCAN, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    @SuppressLint("Range")
    fun getName(): ArrayList<ItemsViewModel> {

        val List:ArrayList<ItemsViewModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME "
        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to
        // read data from the database
        val cursor:Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()){
            do{
                val id :Int
                id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
                val image :String
                image = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_COL))
                val text : String
                text = cursor.getString(cursor.getColumnIndex(DBHelper.TEXT_COL))
                val text_hint :String
                text_hint = cursor.getString(cursor.getColumnIndex(DBHelper.TEXTHINT_COL))

                        val Create = ItemsViewModel(id ,image , text , text_hint)
                List.add(Create)
            }
                while (cursor.moveToNext())
        }
return List
    }

    @SuppressLint("Range")
    fun getHistory(): ArrayList<ScanViewModel> {

        val List:ArrayList<ScanViewModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME_SCAN "
        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to
        // read data from the database
        val cursor:Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()){
            do{
                val id :Int
                id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
                val image :Int
                image = cursor.getInt(cursor.getColumnIndex(DBHelper.IMAGE_COL))
                val text : String
                text = cursor.getString(cursor.getColumnIndex(DBHelper.TEXT_COL))
                val text_hint :String
                text_hint = cursor.getString(cursor.getColumnIndex(DBHelper.TEXTHINT_COL))

                val Scan = ScanViewModel(id , image , text , text_hint)
                List.add(Scan)
            }
            while (cursor.moveToNext())
        }
        return List
    }

    fun deleteHistoryById(id:Int) : Int{

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID_COL ,id)

        val success = db.delete(TABLE_NAME, "id=$id" ,null)
        db.close()
        return success

    }
}
