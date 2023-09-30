package com.alexbernat.albumsfinder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDataModel(
    @SerialName("artistId")
    val artistId: Int,
    @SerialName("collectionId")
    val collectionId: Int,
    @SerialName("collectionName")
    val collectionName: String,
    @SerialName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerialName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerialName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerialName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerialName("collectionType")
    val collectionType: String? = null,
    @SerialName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerialName("copyright")
    val copyright: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("primaryGenreName")
    val primaryGenreName: String? = null,
    @SerialName("releaseDate")
    val releaseDate: String? = null,
    @SerialName("trackCount")
    val trackCount: Int? = null,
    @SerialName("wrapperType")
    val wrapperType: String? = null
)