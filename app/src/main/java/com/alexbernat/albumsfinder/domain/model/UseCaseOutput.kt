package com.alexbernat.albumsfinder.domain.model

sealed class UseCaseOutput<T>(open val data: T?, open val error: DomainError?) {
    data class Success<T>(override val data: T) : UseCaseOutput<T>(data, null)
    data class Error<T>(override val error: DomainError) : UseCaseOutput<T>(null, error)
}