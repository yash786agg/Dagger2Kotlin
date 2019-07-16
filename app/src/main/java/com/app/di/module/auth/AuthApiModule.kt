package com.app.di.module.auth

import com.app.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthApiModule
{
    @Module
    companion object
    {
        @AuthScope
        @Provides
        @JvmStatic
        fun provideAuthApi(retrofit: Retrofit) : AuthApi
        {
            return retrofit.create(AuthApi::class.java)
        }
    }
}