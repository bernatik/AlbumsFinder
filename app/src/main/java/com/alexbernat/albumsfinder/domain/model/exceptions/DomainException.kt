package com.alexbernat.albumsfinder.domain.model.exceptions

sealed class DomainException(throwable: Throwable) : Exception(throwable) {
    data class HttpInternalServerException(val throwable: Throwable) :
        DomainException(throwable)

    data class HttpBadRequest(val throwable: Throwable) : DomainException(throwable)
    data class HttpUnauthorized(val throwable: Throwable) : DomainException(throwable)
    data class HttpForbidden(val throwable: Throwable) : DomainException(throwable)
    data class HttpNotFound(val throwable: Throwable) : DomainException(throwable)
    data class HttpError(val throwable: Throwable) : DomainException(throwable)
    data class Generic(val throwable: Throwable) : DomainException(throwable)
    data class Unknown(val throwable: Throwable) : DomainException(throwable)
    data class Query(val throwable: Throwable) : DomainException(throwable)
}


