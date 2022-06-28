package com.random.animequote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.random.animequote.api.AnimechanAPIClient
import com.random.animequote.model.AnimechanQuoteObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    private fun test() {

            val call: Call<AnimechanQuoteObject> =
                AnimechanAPIClient.getAnimeChanData.getRandomAnimeQuote()

            call.enqueue(object : Callback<AnimechanQuoteObject> {
                override fun onFailure(call: Call<AnimechanQuoteObject>, t: Throwable) {
                    Log.d("API CALL", "Failed API Call")
                }

                override fun onResponse(
                    call: Call<AnimechanQuoteObject>,
                    response: Response<AnimechanQuoteObject>
                ) {
                    var response: AnimechanQuoteObject = response.body()!!
                    Log.d("API CALL", response.anime)


//                var pokeList = response.pokemonList
//                for (pokemon in pokeList) {
//                    Log.d("API CALL", pokemon.name)
//                }

                }
            })

    }


}