package com.random.animequote.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.random.animequote.R
import com.random.animequote.api.AnimechanAPIClient
import com.random.animequote.api.WebSearchClient
import com.random.animequote.databinding.ActivitySingleRandomQuoteBinding
import com.random.animequote.db.DBHelper
import com.random.animequote.model.AnimechanQuoteObject
import com.random.animequote.model.ImageQueryObject
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SingleRandomQuote : AppCompatActivity() {

    private lateinit var binding: ActivitySingleRandomQuoteBinding
    private lateinit var randomQuote: AnimechanQuoteObject
    private var imgLink: String = ""
    private lateinit var imageView: ImageView
    private lateinit var textViewTest: TextView



    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleRandomQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageView = findViewById(R.id.animeImage)

        getRandomAnimeQuote()

        val db = DBHelper(this, null)
        //db.delTable()

        binding.move.setOnClickListener{
            val i = Intent(applicationContext, RecyclerActivity::class.java)
            startActivity(i)
        }

        binding.generateSingleQuoteButton.setOnClickListener {
            getRandomAnimeQuote()
        }

        binding.saveAnime.setOnClickListener{



            // creating variables for values
            // in name and age edit texts
            var anime = findViewById<TextView>(R.id.anime).text.toString()
            var character = findViewById<TextView>(R.id.character).text.toString()
            var quote = findViewById<TextView>(R.id.quote).text.toString()
            var imgurl = imgLink

            var textViewTest = findViewById<TextView>(R.id.character)
            val test1 = textViewTest.text.toString()
            Toast.makeText(this, character, Toast.LENGTH_SHORT).show()

            // calling method to add
            // name to our database
            db.addQuote(anime,character,quote,imgurl)

            // Toast to message on the screen
            Toast.makeText(this, "Quote added to database", Toast.LENGTH_LONG).show()
        }

        /*
        binding.viewSaved.setOnClickListener{


            val cursor = db.getSavedQuotes()

            // moving the cursor to first position and
            // appending value in the text view

            cursor!!.moveToFirst()
            var character = cursor.getString(cursor.getColumnIndex(DBHelper.CHARACTER_COL))
            Toast.makeText(this, character + " added to database", Toast.LENGTH_SHORT).show()

            while(cursor.moveToNext()){
                character = cursor.getString(cursor.getColumnIndex(DBHelper.CHARACTER_COL))
                Toast.makeText(this, character + " added to database", Toast.LENGTH_SHORT).show()
            }



            // at last we close our cursor
            cursor.close()
        }
        */

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
                //var q = query.replace(" ","%20")
                getImage(query)

            }
        })
    }



    fun getImage(q:String){

        val call: Call<ImageQueryObject> =
            WebSearchClient.getImageQueryObject.queryImage(q,"1","10","false", "true")

        call.enqueue(object : Callback<ImageQueryObject> {
            override fun onFailure(call: Call<ImageQueryObject>, t: Throwable) {
                Log.d("API CALL - image", "Failed API Call")

            }

            override fun onResponse(
                call: Call<ImageQueryObject>,
                response: Response<ImageQueryObject>
            ) {
                var response: ImageQueryObject = response.body()!!
                Log.d("API CALL - image quote", response.toString())
                Log.d("API CALL - image quote", response.results.size.toString())
                Log.d("API CALL - image quote", "meyyyep")
                Log.d("API CALL - image quote", response.results.get(0).thumbnail)

                Log.d("API CALL - image quote", "meep")
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", Gson().toJson(response))

                imgLink = response.results.get(0).url
                Picasso.get().load(response.results.get(0).url).into(imageView);


            }
        })

    }

}