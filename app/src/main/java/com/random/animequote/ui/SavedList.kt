package com.random.animequote.ui

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.random.animequote.R
import com.random.animequote.adapter.QuotesAdapter
import com.random.animequote.db.DBHelper
import com.random.animequote.model.Quote
import kotlinx.android.synthetic.main.activity_saved_list.*


class SavedList : AppCompatActivity() {

    //val recyclerView = findViewById<RecyclerView>(R.id.savedList)
    val quotes = ArrayList<Quote>()

    val db = DBHelper(this, null)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_list)



        /*
        val quotes = mutableListOf<Quote>()

        savedList.apply {
            layoutManager = LinearLayoutManager { this@SavedList }
            adapter = QuotesAdapter(quotes)
        }

         */
    }

    fun storeQuotesInArray() {
        var cursor = db.getSavedQuotes()
        if (cursor != null) {
            if (cursor.count == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
            } else {
                while (cursor.moveToNext()) {
                        var newQuote = Quote(
                            cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4)
                        )

                        quotes.add(newQuote)
                    }
            }
        }
    }
}