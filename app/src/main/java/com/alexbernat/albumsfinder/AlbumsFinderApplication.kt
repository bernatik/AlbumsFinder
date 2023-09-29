package com.alexbernat.albumsfinder

import android.app.Application
import com.alexbernat.albumsfinder.di.appModule
import com.alexbernat.albumsfinder.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlbumsFinderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AlbumsFinderApplication)
            modules(
                networkModule,
                appModule
            )
        }
    }
}