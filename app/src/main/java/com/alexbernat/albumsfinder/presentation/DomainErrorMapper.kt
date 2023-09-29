package com.alexbernat.albumsfinder.presentation

import com.alexbernat.albumsfinder.domain.model.DomainError
import com.alexbernat.albumsfinder.presentation.model.PresentationError

fun DomainError.toPresentationError() =
    when (this) {
        is DomainError.GenericError -> PresentationError.UnknownError
        is DomainError.HttpError -> PresentationError.NetworkError
        is DomainError.HttpErrorBadRequest -> PresentationError.NetworkError
        is DomainError.HttpErrorForbidden -> PresentationError.NetworkError
        is DomainError.HttpErrorInternalServerError -> PresentationError.NetworkError
        is DomainError.HttpErrorNotFound -> PresentationError.NetworkError
        is DomainError.HttpErrorUnauthorized -> PresentationError.NetworkError
        is DomainError.QueryError -> PresentationError.QueryError
        is DomainError.EmptyResultError -> PresentationError.EmptyResultError
    }