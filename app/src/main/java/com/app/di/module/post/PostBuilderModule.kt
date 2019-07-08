package com.app.di.module.post

import com.app.ui.main.profile.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class PostBuilderModule
{
    @ContributesAndroidInjector
    abstract fun getPostFragment() : PostFragment
}