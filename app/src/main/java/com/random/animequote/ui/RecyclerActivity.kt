package com.random.animequote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.random.animequote.R
import com.random.animequote.adapter.QuotesAdapter
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: RecyclerView.Adapter<QuotesAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        layoutManager = LinearLayoutManager(this)

        savedList.layoutManager = layoutManager

        adapter = QuotesAdapter()
        savedList.adapter = adapter
    }
}