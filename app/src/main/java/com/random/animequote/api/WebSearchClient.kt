package com.random.animequote.api

import com.google.gson.GsonBuilder
import com.random.animequote.model.ImageQueryObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object WebSearchClient {


    private const val BASE_URL =
        "https://contextualwebsearch-websearch-v1.p.rapidapi.com/"
    val getImageQueryObject: WebSearchImageAPI
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

            return retrofit.create(WebSearchImageAPI::class.java)
        }
}

interface WebSearchImageAPI {
    @Headers(
        "X-RapidAPI-Key: eb026538d4msh041e26a7e6e7ebbp17a967jsn5267b6b76972",
        "X-RapidAPI-Host: contextualwebsearch-websearch-v1.p.rapidapi.com"
    )
    @GET("api/Search/ImageSearchAPI")
    fun queryImage(
        @Query("q") q: String,
        @Query("pageNumber") pageNumber: String,
        @Query("pageSize") pageSize: String,
        @Query("autoCorrect") autoCorrect: String,
        @Query("safeSearch") safeSearch: String,
    ):
            Call<ImageQueryObject>


}