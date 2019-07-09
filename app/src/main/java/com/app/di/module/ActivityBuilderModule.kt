package com.app.di.module

import com.app.di.module.auth.AuthApiModule
import com.app.di.module.auth.AuthViewModelsModule
import com.app.di.module.main.MainFragmentBuildersModule
import com.app.di.module.main.MainViewModelsModule
import com.app.di.module.main.news.NewsApiModule
import com.app.di.module.main.post.PostApiModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.app.ui.auth.AuthActivity
import com.app.ui.main.MainActivity

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthApiModule::class])
    abstract fun getAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, PostApiModule::class, NewsApiModule::class])
    abstract fun getMainActivity(): MainActivity
}