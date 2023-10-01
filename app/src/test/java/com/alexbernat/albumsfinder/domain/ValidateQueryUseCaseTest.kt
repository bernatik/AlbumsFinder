package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.UseCaseOutput
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidateQueryUseCaseTest {

    private val useCase = ValidateQueryUseCase()

    @Test
    fun `verify success execution with valid string`() {
        val result = useCase.execute("valid string")
        assertThat(result).isInstanceOf(UseCaseOutput.Success::class.java)
    }

    @Test
    fun `verify error execution with empty string`() {
        val result = useCase.execute("")
        assertThat(result).isInstanceOf(UseCaseOutput.Error::class.java)
    }
}