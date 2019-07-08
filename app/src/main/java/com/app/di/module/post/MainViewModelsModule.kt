package com.app.di.module.post

import androidx.lifecycle.ViewModel
import com.app.di.annotations.ViewModelKey
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
    abstract fun bindAuthViewModel(viewModel: PostViewModel): ViewModel
}