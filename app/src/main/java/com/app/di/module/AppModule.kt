package com.app.di.module

import android.app.Application
import com.app.util.Constants
import com.app.util.Constants.Companion.TIMEOUT_REQUEST
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.app.util.SessionManager

@Module
class AppModule
{
    @Module
    companion object
    {
        @JvmStatic
        @Singleton
        @Provides
        fun retrofitInstance(): Retrofit
        {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideSharedPreference(application: Application): SharedPreferences {
            return application.getSharedPreferences(Constants.sessionManagerPref, MODE_PRIVATE)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideSessionManager(sharedPreferences: SharedPreferences) : SessionManager
        {
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply()

            return SessionManager(sharedPreferences)
        }



        /*@Provides
        @Singleton
        @JvmStatic
        fun providePreferencesHelper(sharedPreferences: SharedPreferences) : PreferencesHelper
        {
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply()

            return PreferencesHelper(sharedPreferences)
        }*/
    }
}