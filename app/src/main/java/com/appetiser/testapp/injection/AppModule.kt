package com.appetiser.testapp.injection

import android.app.Application
import android.content.Context
import com.appetiser.testapp.data.TrackDataGateway
import com.appetiser.testapp.data.TrackGateway
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun context(application: Application): Context

    @Binds
    @Singleton
    abstract fun trackGateway(gateway: TrackDataGateway): TrackGateway
}