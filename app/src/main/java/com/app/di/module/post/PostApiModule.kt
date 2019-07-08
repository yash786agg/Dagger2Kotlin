package com.app.di.module.post

import com.app.network.post.PostApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostApiModule
{
    @Module
    companion object
    {
        @Provides
        @JvmStatic
        fun provideAuthApi(retrofit: Retrofit) : PostApi
        {
            return retrofit.create(PostApi::class.java)
        }
    }
}