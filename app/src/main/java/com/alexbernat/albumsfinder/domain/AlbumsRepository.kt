package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.Album

interface AlbumsRepository {
    suspend fun fetchAlbums(query: String): List<Album>
}