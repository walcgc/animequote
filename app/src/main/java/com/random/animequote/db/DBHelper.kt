package com.random.animequote.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given


        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                ANIME_COL + " TEXT," +
                CHARACTER_COL + " TEXT," +
                QUOTE_COL + " TEXT," +
                IMG_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun delDB(db: SQLiteDatabase){
        val delQuery = ("drop table if exists" + TABLE_NAME)
        db.execSQL(delQuery)
    }

    // This method is for adding data in our database
    fun addQuote(anime : String, character : String, quote : String, image_url : String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(ANIME_COL, anime)
        values.put(CHARACTER_COL, character)
        values.put(QUOTE_COL, quote)
        values.put(IMG_COL, image_url)

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

    // below method is to get
    // all data from our database
    fun checkIfQuoteExists(anime : String, character : String, quote : String): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE (" +
                ANIME_COL + " = " + anime + " AND " +
                CHARACTER_COL + " = " + character + " AND " +
                quote + " = " + quote + ") "
            , null)

    }

    fun getSavedQuotes(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "anime_db"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "saved_quotes"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val ANIME_COL = "anime"

        // below is the variable for age column
        val CHARACTER_COL = "character"

        // below is the variable for name column
        val QUOTE_COL = "quote"

        // below is the variable for age column
        val IMG_COL = "img_url"
    }
}