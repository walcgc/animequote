package com.random.animequote.ui

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.random.animequote.R
import com.random.animequote.api.WebSearchClient
import com.random.animequote.databinding.ActivityShowSavedQuoteBinding
import com.random.animequote.databinding.ActivitySingleRandomQuoteBinding
import com.random.animequote.db.DBHelper
import com.random.animequote.model.ImageQueryObject
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowSavedQuote : AppCompatActivity() {

    private lateinit var binding: ActivityShowSavedQuoteBinding
    private lateinit var imageView: ImageView

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_saved_quote)
        binding = ActivityShowSavedQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageView = findViewById(R.id.animeImage)

        var id = intent.getStringExtra("CARD_ID")

        Toast.makeText(this, id + "test", Toast.LENGTH_SHORT).show()

        val db = DBHelper(this, null)
        var cursor: Cursor

        cursor = db.getQuoteByID(id)!!


        cursor!!.moveToFirst()
        var anime = cursor.getString(cursor.getColumnIndex(DBHelper.ANIME_COL))
        var character = cursor.getString(cursor.getColumnIndex(DBHelper.CHARACTER_COL))
        var quote = cursor.getString(cursor.getColumnIndex(DBHelper.QUOTE_COL))
        var imgurl = cursor.getString(cursor.getColumnIndex(DBHelper.IMG_COL))


        Toast.makeText(this, character, Toast.LENGTH_SHORT).show()

        // at last we close our cursor
        cursor.close()

        binding.anime.setText(anime)
        binding.character.setText(character)
        binding.quote.setText(quote)

        Picasso.get().load(imgurl).into(imageView);
    }


}