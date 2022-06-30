package com.random.animequote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.random.animequote.R
import com.random.animequote.fragments.RAQFragment
import com.random.animequote.fragments.SavedQuoteFragment
import kotlinx.android.synthetic.main.activity_ani_quote_main.*

class AniQuoteMain : AppCompatActivity() {

    //https://www.youtube.com/watch?v=v8MbOjBCu0o

    private val raqFragment = RAQFragment()
    private val sqFragment = SavedQuoteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ani_quote_main)
        replaceFragment(raqFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_dashboard -> replaceFragment(raqFragment)
                R.id.ic_collections -> replaceFragment(sqFragment)
            }
            true

        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}