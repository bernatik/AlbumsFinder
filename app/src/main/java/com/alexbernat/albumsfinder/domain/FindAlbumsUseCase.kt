package com.alexbernat.albumsfinder.domain

import com.alexbernat.albumsfinder.domain.model.Album
import com.alexbernat.albumsfinder.domain.model.UseCaseOutput
import com.alexbernat.albumsfinder.domain.model.exceptions.DomainException
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
            UseCaseOutput.Success(value = result)
        } catch (e: Exception) {
            UseCaseOutput.Error(
                exception = (e as? DomainException) ?: DomainException.Unknown(e)
            )
        }
    }
}