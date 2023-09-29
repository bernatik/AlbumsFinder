package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.Album
import com.alexbernat.albumsfinder.domain.model.DomainError
import com.alexbernat.albumsfinder.domain.model.EmptyResponseException
import com.alexbernat.albumsfinder.domain.model.UseCaseOutput
import com.alexbernat.albumsfinder.domain.model.toDomainError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FindAlbumsUseCase(
    private val albumsRepository: AlbumsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun execute(query: String): UseCaseOutput<List<Album>> = withContext(dispatcher) {
        return@withContext try {
            val result = albumsRepository.fetchAlbums(query)
            if (result.isEmpty()) {
                UseCaseOutput.Error<List<Album>>(
                    error = DomainError.EmptyResultError(
                        EmptyResponseException()
                    )
                )
            } else {
                UseCaseOutput.Success(data = result)
            }
        } catch (e: Exception) {
            UseCaseOutput.Error(error = e.toDomainError())
        }
    }
}