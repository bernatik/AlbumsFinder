package com.alexbernat.albumsfinder.data.network

import com.alexbernat.albumsfinder.data.model.SearchResultDataModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ITunesAlbumsAPI(private val client: HttpClient) {

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/search"
    }

    suspend fun fetchAlbums(query: String): SearchResultDataModel =
        client.get(BASE_URL) {
            url {
                parameter("term", query)
                parameter("media", "music")
                parameter("entity", "album")
                parameter("attribute", "artistTerm")
            }
        }.body()
}