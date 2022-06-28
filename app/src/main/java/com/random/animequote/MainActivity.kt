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