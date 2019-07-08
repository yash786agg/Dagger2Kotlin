package com.app.di

import android.app.Application
import javax.inject.Singleton
import com.app.daggerkotlin.BaseApplication
import com.app.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import android.content.SharedPreferences
import com.app.util.PreferencesHelper
import com.app.util.SessionManager

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class, ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

   /* fun prefManager(): SharedPreferences

    fun sessionManager(): SessionManager*/

    //fun preferencesHelper(): PreferencesHelper
}
