package com.alexbernat.albumsfinder.di

import com.alexbernat.albumsfinder.data.network.ITunesAlbumsAPI
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> {
        Android.create()
    }
    single {
        ITunesAlbumsAPI(
            engine = get()
        )
    }
}