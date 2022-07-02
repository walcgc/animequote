package com.random.animequote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.random.animequote.R
import com.random.animequote.adapter.QuotesAdapter
import com.random.animequote.db.DBHelper
import com.random.animequote.model.Quote
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: RecyclerView.Adapter<QuotesAdapter.ViewHolder>? = null

    val db = DBHelper(this, null)
    val quotes = ArrayList<Quote>()
    val id = ArrayList<String>()
    val anime = ArrayList<String>()
    val character = ArrayList<String>()
    val quote = ArrayList<String>()
    val imgurl = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        layoutManager = LinearLayoutManager(this)

        savedList.layoutManager = layoutManager

        storeQuotesInArray()
        adapter = QuotesAdapter(this, id, anime, character, quote, imgurl)

        savedList.adapter = adapter
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
                    id.add(cursor.getString(0))
                    anime.add(cursor.getString(1))
                    character.add(cursor.getString(2))
                    quote.add(cursor.getString(3))
                    imgurl.add(cursor.getString(4))
                }
            }
        }
    }
}