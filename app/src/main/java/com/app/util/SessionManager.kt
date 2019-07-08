package com.app.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

// Link -> https://stackoverflow.com/questions/48920133/singleton-sharedpreferences-with-dagger-2

@Singleton
class SessionManager @Inject constructor(private val sharedPreferences: SharedPreferences)
{
    /*
     * LogIn
     * */

    fun isLogIn(): Boolean?
    {
        return sharedPreferences.getBoolean(Constants.logInSess, false)
    }

    fun setLogIn(value: Boolean)
    {
        sharedPreferences.edit().putBoolean(Constants.logInSess, value).apply()
    }

    fun isUserId(): Int?
    {
        return sharedPreferences.getInt(Constants.userIdSess, 0)
    }

    fun setUserId(value: Int)
    {
        sharedPreferences.edit().putInt(Constants.userIdSess, value).apply()
    }
}