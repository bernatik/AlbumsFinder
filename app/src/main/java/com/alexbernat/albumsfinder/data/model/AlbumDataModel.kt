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
    val artworkUrl100: String?,
    @SerialName("collectionCensoredName")
    val collectionCensoredName: String?,
    @SerialName("collectionExplicitness")
    val collectionExplicitness: String?,
    @SerialName("collectionPrice")
    val collectionPrice: Double?,
    @SerialName("collectionType")
    val collectionType: String?,
    @SerialName("collectionViewUrl")
    val collectionViewUrl: String?,
    @SerialName("copyright")
    val copyright: String?,
    @SerialName("country")
    val country: String?,
    @SerialName("currency")
    val currency: String?,
    @SerialName("primaryGenreName")
    val primaryGenreName: String?,
    @SerialName("releaseDate")
    val releaseDate: String?,
    @SerialName("trackCount")
    val trackCount: Int?,
    @SerialName("wrapperType")
    val wrapperType: String?
)