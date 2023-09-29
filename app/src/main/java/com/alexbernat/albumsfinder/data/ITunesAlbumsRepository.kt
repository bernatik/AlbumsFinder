package com.alexbernat.albumsfinder.data

import com.alexbernat.albumsfinder.data.model.toDomainModel
import com.alexbernat.albumsfinder.data.network.ITunesAlbumsAPI
import com.alexbernat.albumsfinder.domain.AlbumsRepository
import com.alexbernat.albumsfinder.domain.model.Album

class ITunesAlbumsRepository(
    private val albumsAPI: ITunesAlbumsAPI
) : AlbumsRepository {

    override suspend fun fetchAlbums(query: String): List<Album> {
        return albumsAPI.fetchAlbums(query).results.map { it.toDomainModel() }
    }
}