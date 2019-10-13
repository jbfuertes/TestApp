package com.appetiser.testapp.injection

import android.app.Application
import com.appetiser.testapp.TestApplication
import com.appetiser.testapp.common.ExecutionThread
import com.appetiser.testapp.common.PostExecutionThread
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        DataModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun executionThread(thread: ExecutionThread): Builder

        @BindsInstance
        fun postExecutionThread(thread: PostExecutionThread): Builder

        fun build(): AppComponent
    }

    fun inject(application: TestApplication)

}