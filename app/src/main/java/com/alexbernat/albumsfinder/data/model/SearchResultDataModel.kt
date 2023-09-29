package com.alexbernat.albumsfinder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultDataModel(
    @SerialName("resultCount")
    val resultCount: Int,
    @SerialName("results")
    val results: List<AlbumDataModel>
)