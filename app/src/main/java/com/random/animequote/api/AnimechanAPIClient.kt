package com.random.animequote.api

import com.google.gson.GsonBuilder
import com.random.animequote.model.AnimechanQuoteObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object AnimechanAPIClient {
    private const val BASE_URL = "https://animechan.vercel.app/api/"
    val getAnimeChanData:AnimechanAPI
        get() {
            val gson = GsonBuilder().setLenient().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(AnimechanAPI::class.java)
        }
}

interface AnimechanAPI {
    @GET("random")
    fun getRandomAnimeQuote(): Call<AnimechanQuoteObject>
}