package com.alexbernat.albumsfinder.presentation

import com.alexbernat.albumsfinder.presentation.model.PresentationError
import com.alexbernat.albumsfinder.domain.model.exceptions.DomainException

fun DomainException.toPresentationError() =
    when (this) {
        is DomainException.Generic -> PresentationError.UnknownError
        is DomainException.HttpBadRequest -> PresentationError.NetworkError
        is DomainException.HttpError -> PresentationError.NetworkError
        is DomainException.HttpForbidden -> PresentationError.NetworkError
        is DomainException.HttpInternalServerException -> PresentationError.NetworkError
        is DomainException.HttpNotFound -> PresentationError.NetworkError
        is DomainException.HttpUnauthorized -> PresentationError.NetworkError
        is DomainException.Query -> PresentationError.QueryError
        is DomainException.Unknown -> PresentationError.UnknownError
    }