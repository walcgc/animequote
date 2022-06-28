package com.random.animequote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.random.animequote.api.AnimechanAPIClient
import com.random.animequote.api.AnimechanAPIFunctions
import com.random.animequote.databinding.ActivityMainBinding
import com.random.animequote.databinding.ActivitySingleRandomQuoteBinding
import com.random.animequote.model.AnimechanQuoteObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleRandomQuote : AppCompatActivity() {

    private lateinit var binding: ActivitySingleRandomQuoteBinding
    private lateinit var randomQuote: AnimechanQuoteObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleRandomQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomAnimeQuote()

        binding.generateSingleQuoteButton.setOnClickListener {
            getRandomAnimeQuote()
        }

    }


    fun getRandomAnimeQuote(){
        val call: Call<AnimechanQuoteObject> =
            AnimechanAPIClient.getAnimeChanData.getRandomAnimeQuote()

        call.enqueue(object : Callback<AnimechanQuoteObject> {
            override fun onFailure(call: Call<AnimechanQuoteObject>, t: Throwable) {
                Log.d("API CALL - random quote", "Failed API Call")
                binding.anime.setText("error")
                binding.character.setText("error")
                binding.quote.setText("error")
            }

            override fun onResponse(
                call: Call<AnimechanQuoteObject>,
                response: Response<AnimechanQuoteObject>
            ) {
                var response: AnimechanQuoteObject = response.body()!!
                Log.d("API CALL - random quote", response.anime)
                binding.anime.setText(response.anime)
                binding.character.setText(response.character)
                binding.quote.setText(response.quote)

            }
        })
    }

}