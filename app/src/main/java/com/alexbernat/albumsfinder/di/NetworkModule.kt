package com.alexbernat.albumsfinder.di

import com.alexbernat.albumsfinder.data.network.AndroidHttpLogger
import com.alexbernat.albumsfinder.data.network.ITunesAlbumsAPI
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module

val networkModule = module {
    single {
        ITunesAlbumsAPI(
            engine = Android.create(),
            httpLogger = get()
        )
    }
    single<HttpClientEngine> {
        Android.create()
    }
    single<Logger> {
        AndroidHttpLogger()
    }
}