package com.random.animequote.api

import android.util.Log
import com.random.animequote.model.AnimechanQuoteObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimechanAPIFunctions {

    fun getRandomQuote(): AnimechanQuoteObject? {

        val call: Call<AnimechanQuoteObject> =
            AnimechanAPIClient.getAnimeChanData.getRandomAnimeQuote()
        var responseObject: AnimechanQuoteObject? = null

        call.enqueue(object : Callback<AnimechanQuoteObject> {
            override fun onFailure(call: Call<AnimechanQuoteObject>, t: Throwable) {
                Log.d("API CALL - random quote", "Failed API Call")

            }

            override fun onResponse(
                call: Call<AnimechanQuoteObject>,
                response: Response<AnimechanQuoteObject>
            ) {
                var response: AnimechanQuoteObject = response.body()!!
                Log.d("API CALL - random quote", response.anime)
                responseObject = response
            }
        })

        return responseObject

    }

    fun getTenRandomQuote() {

        val call: Call<List<AnimechanQuoteObject>> =
            AnimechanAPIClient.getAnimeChanData.getTenRandomQuotes()

        call.enqueue(object : Callback<List<AnimechanQuoteObject>?> {
            override fun onFailure(call: Call<List<AnimechanQuoteObject>?>, t: Throwable) {
                Log.d("API CALL", "Failed API Call")
            }

            override fun onResponse(
                call: Call<List<AnimechanQuoteObject>?>,
                response: Response<List<AnimechanQuoteObject>?>
            ) {

                var response = response.body()!!
                for (item in response) {
                    Log.d("API CALL 10", item.toString())
                }


            }
        })

    }
}