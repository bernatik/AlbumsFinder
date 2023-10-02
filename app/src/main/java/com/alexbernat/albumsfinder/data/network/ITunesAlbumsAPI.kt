package com.alexbernat.albumsfinder.data.network

import com.alexbernat.albumsfinder.data.model.SearchResultDataModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

class ITunesAlbumsAPI(engine: HttpClientEngine) {

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/search"
    }

    private val httpClient = HttpClient(engine) {
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
    }

    suspend fun fetchAlbums(query: String): SearchResultDataModel =
        httpClient.get(BASE_URL) {
            url {
                parameter("term", query)
                parameter("media", "music")
                parameter("entity", "album")
                parameter("attribute", "artistTerm")
            }
        }.body()
}