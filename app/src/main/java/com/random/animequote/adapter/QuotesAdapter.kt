package com.random.animequote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.random.animequote.R
import com.random.animequote.db.DBHelper
import com.random.animequote.model.Quote

class QuotesAdapter(
    context: Context,
    anime: ArrayList<String>,
    character: ArrayList<String>,
    quote: ArrayList<String>,
    imgurl: ArrayList<String>
) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    var anime = anime
    var character = character
    lateinit var quote: ArrayList<String>
    lateinit var imgurl: ArrayList<String>


    var titles = arrayOf("1", "2", "1")
    var details = arrayOf("2", "1", "3")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_saved_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return anime.size
    }

    override fun onBindViewHolder(holder: QuotesAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = anime[position]
        holder.itemDetail.text = character[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            //itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

        }
    }

}