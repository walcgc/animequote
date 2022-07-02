package com.random.animequote.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.random.animequote.R
import com.random.animequote.ui.ShowSavedQuote
import com.squareup.picasso.Picasso


class QuotesAdapter(
    context: Context,
    id: ArrayList<String>,
    anime: ArrayList<String>,
    character: ArrayList<String>,
    quote: ArrayList<String>,
    imgurl: ArrayList<String>
) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    var context = context
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

            itemView.setOnClickListener{ v: View ->
                val position: Int = absoluteAdapterPosition
                //change to id and not position?
                //Toast.makeText(itemView.context, "Clicked on item - ${position + 1}", Toast.LENGTH_SHORT).show()
                //id.get(position)
                val intent = Intent(context, ShowSavedQuote::class.java)
                intent.putExtra("CARD_ID", id.get(position))
                context.startActivity(intent)

            }

        }
    }

}