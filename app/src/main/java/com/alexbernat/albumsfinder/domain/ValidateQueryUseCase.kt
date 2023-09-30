package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.UseCaseOutput
import com.alexbernat.albumsfinder.domain.model.exceptions.DomainException

class ValidateQueryUseCase {

    fun execute(query: String): UseCaseOutput<String> {
        return if (query.isEmpty() || query.isBlank()) {
            UseCaseOutput.Error(
                exception = DomainException.Query(
                    IllegalArgumentException("Not available query: $query")
                )
            )
        } else {
            UseCaseOutput.Success(query)
        }
    }
}