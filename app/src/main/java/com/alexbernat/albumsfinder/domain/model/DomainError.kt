package com.alexbernat.albumsfinder.domain.model

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

sealed class DomainError(open val exception: Exception) {
    data class HttpErrorInternalServerError(override val exception: Exception) :
        DomainError(exception)

    data class HttpErrorBadRequest(override val exception: Exception) : DomainError(exception)
    data class HttpErrorUnauthorized(override val exception: Exception) : DomainError(exception)
    data class HttpErrorForbidden(override val exception: Exception) : DomainError(exception)
    data class HttpErrorNotFound(override val exception: Exception) : DomainError(exception)
    data class HttpError(override val exception: Exception) : DomainError(exception)
    data class GenericError(override val exception: Exception) : DomainError(exception)
    data class QueryError(override val exception: Exception) : DomainError(exception)
    data class EmptyResultError(override val exception: Exception) : DomainError(exception)
}

fun Exception.toDomainError() = when (this) {
    is ServerResponseException -> DomainError.HttpErrorInternalServerError(this)
    is ClientRequestException ->
        when (this.response.status.value) {
            400 -> DomainError.HttpErrorBadRequest(this)
            401 -> DomainError.HttpErrorUnauthorized(this)
            403 -> DomainError.HttpErrorForbidden(this)
            404 -> DomainError.HttpErrorNotFound(this)
            else -> DomainError.HttpError(this)
        }

    is RedirectResponseException -> DomainError.HttpError(this)
    else -> DomainError.GenericError(this)
}
