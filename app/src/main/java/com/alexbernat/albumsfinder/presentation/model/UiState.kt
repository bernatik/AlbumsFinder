package com.alexbernat.albumsfinder.presentation.model

import com.alexbernat.albumsfinder.domain.model.Album

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class SearchResult(val albums: List<Album>) : UiState()
    data class Error(val data: PresentationError) : UiState()
}
