package com.random.animequote.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.random.animequote.R
import com.random.animequote.api.AnimechanAPIClient
import com.random.animequote.databinding.ActivitySingleRandomQuoteBinding
import com.random.animequote.model.AnimechanQuoteObject
import kotlinx.android.synthetic.main.fragment_r_a_q.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RAQFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RAQFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: ActivitySingleRandomQuoteBinding
    private lateinit var randomQuote: AnimechanQuoteObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        binding = ActivitySingleRandomQuoteBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        getRandomAnimeQuote()

        binding.generateSingleQuoteButton.setOnClickListener {
            getRandomAnimeQuote()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_r_a_q, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        /*
        raq.setOnClickListener(){
            Toast.makeText(context, "raqqq", Toast.LENGTH_SHORT).show()
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

            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RAQFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RAQFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}