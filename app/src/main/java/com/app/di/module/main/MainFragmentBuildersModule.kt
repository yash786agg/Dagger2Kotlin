package com.app.di.module.main

import com.app.ui.main.news.NewsFragment
import com.app.ui.main.profile.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainFragmentBuildersModule
{
    @ContributesAndroidInjector
    abstract fun getPostFragment() : PostFragment

    @ContributesAndroidInjector
    abstract fun getNewsFragment() : NewsFragment
}