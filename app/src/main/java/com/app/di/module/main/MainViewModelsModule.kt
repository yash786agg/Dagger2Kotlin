package com.app.di.module.main

import androidx.lifecycle.ViewModel
import com.app.di.annotations.ViewModelKey
import com.app.ui.main.news.NewsViewModel
import com.app.ui.main.profile.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule
{
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModelModel(viewModel: NewsViewModel): ViewModel
}