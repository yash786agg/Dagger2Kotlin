package com.app.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.app.daggerkotlin.R
import com.app.ui.main.profile.PostFragment
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

        Log.e(TAG, "MainActivity sharedPreferences: ${sharedPreferences}")

        Log.e(TAG, "MainActivity PreferencesHelper: ${sessionManager}")

        Log.e(TAG, "PreferencesHelper isLogIn first: ${sessionManager.isLogIn()}")

        supportFragmentManager.beginTransaction().replace(R.id.main_container,PostFragment()).commit()
    }
}