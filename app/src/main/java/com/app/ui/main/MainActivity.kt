package com.app.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import com.app.daggerkotlin.R
import com.app.ui.main.news.NewsFragment
import com.app.util.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity()
{
    @Inject lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var sessionManager : SessionManager

    private val TAG : String = "AuthActivity"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportFragmentManager.beginTransaction().replace(R.id.main_container,PostFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.main_container,NewsFragment()).commit()
    }
}