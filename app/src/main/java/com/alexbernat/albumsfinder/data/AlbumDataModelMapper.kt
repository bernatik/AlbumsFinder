package com.alexbernat.albumsfinder.data

import com.alexbernat.albumsfinder.data.model.AlbumDataModel
import com.alexbernat.albumsfinder.domain.model.Album

internal fun AlbumDataModel.toDomainModel() =
    Album(
        id = collectionId,
        title = collectionName,
        imageUrl = artworkUrl100
    )