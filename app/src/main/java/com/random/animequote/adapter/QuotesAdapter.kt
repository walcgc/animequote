package com.random.animequote.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.random.animequote.R

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    var titles = arrayOf("1", "2", "1")
    var details = arrayOf("2", "1", "3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_saved_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return titles.size
    }

    override fun onBindViewHolder(holder: QuotesAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = titles[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init{
            //itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

        }
    }

}