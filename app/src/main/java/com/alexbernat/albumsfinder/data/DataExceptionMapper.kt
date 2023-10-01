package com.alexbernat.albumsfinder.data

import com.alexbernat.albumsfinder.domain.model.exceptions.DomainException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

fun Exception.toDomainException(): DomainException = when (this) {
    is ServerResponseException -> DomainException.HttpInternalServerException(this)
    is ClientRequestException ->
        when (this.response.status.value) {
            400 -> DomainException.HttpBadRequest(this)
            401 -> DomainException.HttpUnauthorized(this)
            403 -> DomainException.HttpForbidden(this)
            404 -> DomainException.HttpNotFound(this)
            else -> DomainException.HttpError(this)
        }

    is RedirectResponseException -> DomainException.HttpError(this)
    else -> DomainException.Generic(this)
}