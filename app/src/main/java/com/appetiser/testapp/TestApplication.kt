package com.appetiser.testapp

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.appetiser.testapp.common.BackgroundThread
import com.appetiser.testapp.common.MainThread
import com.appetiser.testapp.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class TestApplication : MultiDexApplication(), HasActivityInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
    }

    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .executionThread(BackgroundThread())
            .postExecutionThread(MainThread())
            .build()
            .inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}