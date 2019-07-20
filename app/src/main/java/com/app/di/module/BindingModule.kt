package com.app.di.module

import com.app.di.annotations.DataBinding
import com.app.ui.main.news.NewsListBindingAdapter
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import dagger.Module
import dagger.Provides

@Module
object BindingModule
{
    @Provides @DataBinding
    fun provideNewsListBindingAdapter(fresco: PipelineDraweeControllerBuilder) : NewsListBindingAdapter = NewsListBindingAdapter(fresco)
}