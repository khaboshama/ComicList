package com.khaled.comiclist

import android.app.Application
import com.khaled.comiclist.di.FeaturesKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            modules(FeaturesKoinModules.allModules)
        }
    }
}