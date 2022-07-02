package com.random.animequote.adapter

import android.content.Context
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.random.animequote.R
import com.squareup.picasso.Picasso

class QuotesAdapter(
    context: Context,
    id: ArrayList<String>,
    anime: ArrayList<String>,
    character: ArrayList<String>,
    quote: ArrayList<String>,
    imgurl: ArrayList<String>
) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    var id = id
    var anime = anime
    var character = character
    var quote = quote
    var imgurl = imgurl


    //var titles = arrayOf("1", "2", "1")
    //var details = arrayOf("2", "1", "3")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_saved_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return anime.size
    }

    override fun onBindViewHolder(holder: QuotesAdapter.ViewHolder, position: Int) {
        holder.animeTitle.text = anime[position]
        holder.characterTitle.text = character[position]
        holder.quoteID.text = id[position]
        //holder.imageThumbnail.setImageBitmap()
        Picasso.get().load(imgurl[position]).into(holder.imageThumbnail);
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var itemImage: ImageView
        var animeTitle: TextView
        var characterTitle: TextView
        var quoteID: TextView
        var imageThumbnail: ImageView

        init {
            //itemImage = itemView.findViewById(R.id.item_image)
            animeTitle = itemView.findViewById(R.id.item_title)
            characterTitle = itemView.findViewById(R.id.item_detail)
            quoteID = itemView.findViewById(R.id.quote_id)
            imageThumbnail = itemView.findViewById(R.id.item_image)

        }
    }

}