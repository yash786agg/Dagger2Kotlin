package com.app.di.module.main.post

import com.app.di.module.main.MainScope
import com.app.network.post.PostApi
import com.app.ui.main.profile.PostAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PostApiModule
{
    @Module
    companion object
    {
        @MainScope
        @Provides
        @JvmStatic
        fun providePostApi(retrofit: Retrofit) : PostApi
        {
            return retrofit.create(PostApi::class.java)
        }

        @MainScope
        @Provides
        @JvmStatic
        fun providePostAdapter() : PostAdapter {
          return PostAdapter()
        }
    }
}