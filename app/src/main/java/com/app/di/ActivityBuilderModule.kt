package com.app.di

import com.app.di.module.auth.AuthApiModule
import com.app.di.module.auth.AuthViewModelsModule
import com.app.di.module.post.MainViewModelsModule
import com.app.di.module.post.PostApiModule
import com.app.di.module.post.PostBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.app.ui.auth.AuthActivity
import com.app.ui.main.MainActivity

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthApiModule::class])
    abstract fun getAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [PostBuilderModule::class, MainViewModelsModule::class, PostApiModule::class])
    abstract fun getMainActivity(): MainActivity
}