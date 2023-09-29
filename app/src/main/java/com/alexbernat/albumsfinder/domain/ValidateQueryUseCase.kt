package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.DomainError
import com.alexbernat.albumsfinder.domain.model.UseCaseOutput

class ValidateQueryUseCase {

    fun execute(query: String): UseCaseOutput<String> {
        return if (query.isEmpty() || query.isBlank()) {
            UseCaseOutput.Error(DomainError.QueryError(
                IllegalArgumentException("Not available query: $query")
            ))
        } else {
            UseCaseOutput.Success(query)
        }
    }
}