package com.appetiser.testapp.injection

import androidx.lifecycle.ViewModel
import com.appetiser.testapp.presenter.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun listFragment(): ListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun listViewModel(viewModel: ListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun trackDetailFragment(): TrackDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(TrackDetailViewModel::class)
    abstract fun trackDetailViewModel(viewModel: TrackDetailViewModel): ViewModel

}