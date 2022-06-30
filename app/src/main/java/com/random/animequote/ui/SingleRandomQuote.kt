package com.random.animequote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.random.animequote.api.AnimechanAPIClient
import com.random.animequote.api.WebSearchClient
import com.random.animequote.databinding.ActivitySingleRandomQuoteBinding
import com.random.animequote.model.AnimechanQuoteObject
import com.random.animequote.model.ImageQueryObject
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SingleRandomQuote : AppCompatActivity() {

    private lateinit var binding: ActivitySingleRandomQuoteBinding
    private lateinit var randomQuote: AnimechanQuoteObject
    private lateinit var imgLink: String


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

                var query = "${response.anime} - ${response.character}"
                var q = query.replace(" ","%20")
                getImage(q)

            }
        })
    }

    fun getImage(q:String){

        val call: Call<ImageQueryObject> =
            WebSearchClient.getImageQueryObject.getImage(q,"1","10","true")

        call.enqueue(object : Callback<ImageQueryObject> {
            override fun onFailure(call: Call<ImageQueryObject>, t: Throwable) {
                Log.d("API CALL - image", "Failed API Call")

            }

            override fun onResponse(
                call: Call<ImageQueryObject>,
                response: Response<ImageQueryObject>
            ) {
                var response: ImageQueryObject = response.body()!!
                Log.d("API CALL - image quote", response.results.toString())
                Log.d("API CALL - image quote", response.results?.size.toString())
                Log.d("API CALL - image quote", "meep")


            }
        })

    }

}