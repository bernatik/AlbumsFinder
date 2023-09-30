package com.alexbernat.albumsfinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexbernat.albumsfinder.domain.FindAlbumsUseCase
import com.alexbernat.albumsfinder.domain.ValidateQueryUseCase
import com.alexbernat.albumsfinder.presentation.model.UiState
import com.alexbernat.albumsfinder.domain.model.UseCaseOutput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchUseCase: FindAlbumsUseCase,
    private val validateQueryUseCase: ValidateQueryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), UiState.Idle)

    fun search(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = when (val validatedQuery = validateQueryUseCase.execute(query)) {
                is UseCaseOutput.Error -> {
                    UiState.Error(validatedQuery.exception.toPresentationError())
                }

                is UseCaseOutput.Success -> {
                    when (val searchResult = searchUseCase.execute(query)) {
                        is UseCaseOutput.Success -> {
                            UiState.SearchResult(searchResult.value)
                        }

                        is UseCaseOutput.Error -> {
                            UiState.Error(searchResult.exception.toPresentationError())
                        }
                    }
                }
            }
        }
    }

}