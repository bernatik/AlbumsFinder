package com.alexbernat.albumsfinder.di

import android.util.Log
import com.alexbernat.albumsfinder.data.network.ITunesAlbumsAPI
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        ITunesAlbumsAPI(
            client = get()
        )
    }

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                register(
                    ContentType.Text.JavaScript, KotlinxSerializationConverter(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                )
            }
            install(Logging) {
                logger = CustomAndroidHttpLogger
                level = LogLevel.ALL
            }
        }
    }
}

/**
 * It's required since both [DEFAULT] and [ANDROID] uses SLF4J, which does nothing in Android by default
 * (prints warning about "no-operation (NOP) logger implementation").
 */
private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "HttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}