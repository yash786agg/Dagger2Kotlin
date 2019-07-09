package com.app.di.module.main.news

import com.app.network.news.NewsApi
import com.app.ui.main.news.NewsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NewsApiModule
{
    @Module
    companion object
    {
        @Provides
        @JvmStatic
        fun providePostApi(retrofit: Retrofit) : NewsApi
        {
            return retrofit.create(NewsApi::class.java)
        }

        @Provides
        @JvmStatic
        fun provideNewsAdapter() : NewsAdapter {
            return NewsAdapter()
        }
    }
}