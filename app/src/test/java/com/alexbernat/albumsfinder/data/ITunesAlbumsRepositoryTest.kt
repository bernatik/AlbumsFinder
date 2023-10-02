package com.alexbernat.albumsfinder.data

import com.alexbernat.albumsfinder.data.network.ITunesAlbumsAPI
import com.alexbernat.albumsfinder.CoroutineTest
import com.alexbernat.albumsfinder.domain.model.Album
import com.alexbernat.albumsfinder.domain.model.exceptions.DomainException
import com.google.common.truth.Truth.assertThat
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ITunesAlbumsRepositoryTest : CoroutineTest() {

    companion object {
        private const val TEST_QUERY = "the beatles"
        private const val EXPECTED_RESPONSE = """
{
 "resultCount":3,
 "results": [
{"wrapperType":"collection", "collectionType":"Album", "artistId":136975, "collectionId":1440833098, "amgArtistId":3644, "artistName":"The Beatles", "collectionName":"1", "collectionCensoredName":"1", "artistViewUrl":"https://music.apple.com/us/artist/the-beatles/136975?uo=4", "collectionViewUrl":"https://music.apple.com/us/album/1/1440833098?uo=4", "artworkUrl60":"https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/f2/98/fb/f298fb48-1e0e-6ad4-4cff-fb824b77f02e/15UMGIM59587.rgb.jpg/60x60bb.jpg", "artworkUrl100":"https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/f2/98/fb/f298fb48-1e0e-6ad4-4cff-fb824b77f02e/15UMGIM59587.rgb.jpg/100x100bb.jpg", "collectionPrice":12.99, "collectionExplicitness":"notExplicit", "trackCount":27, "copyright":"This Compilation ℗ 2015 Calderstone Productions Limited (a division of Universal Music Group) / Apple Corps Ltd.", "country":"USA", "currency":"USD", "releaseDate":"2000-11-13T08:00:00Z", "primaryGenreName":"Rock"}, 
{"wrapperType":"collection", "collectionType":"Album", "artistId":136975, "collectionId":1441133180, "amgArtistId":3644, "artistName":"The Beatles", "collectionName":"The Beatles (The White Album)", "collectionCensoredName":"The Beatles (The White Album)", "artistViewUrl":"https://music.apple.com/us/artist/the-beatles/136975?uo=4", "collectionViewUrl":"https://music.apple.com/us/album/the-beatles-the-white-album/1441133180?uo=4", "artworkUrl60":"https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/fa/5b/89/fa5b898d-bad6-e053-4195-260e5c74f2bb/00602567725466.rgb.jpg/60x60bb.jpg", "artworkUrl100":"https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/fa/5b/89/fa5b898d-bad6-e053-4195-260e5c74f2bb/00602567725466.rgb.jpg/100x100bb.jpg", "collectionPrice":19.99, "collectionExplicitness":"notExplicit", "trackCount":32, "copyright":"This Compilation ℗ 2009 Calderstone Productions Limited (a division of Universal Music Group)", "country":"USA", "currency":"USD", "releaseDate":"1968-11-22T08:00:00Z", "primaryGenreName":"Rock"}, 
{"wrapperType":"collection", "collectionType":"Album", "artistId":136975, "collectionId":1441133100, "amgArtistId":3644, "artistName":"The Beatles", "collectionName":"The Beatles 1967-1970 (The Blue Album)", "collectionCensoredName":"The Beatles 1967-1970 (The Blue Album)", "artistViewUrl":"https://music.apple.com/us/artist/the-beatles/136975?uo=4", "collectionViewUrl":"https://music.apple.com/us/album/the-beatles-1967-1970-the-blue-album/1441133100?uo=4", "artworkUrl60":"https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/a6/8b/65/a68b657c-cac6-68e6-3bde-b79d58fbc795/18UMGIM30762.rgb.jpg/60x60bb.jpg", "artworkUrl100":"https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/a6/8b/65/a68b657c-cac6-68e6-3bde-b79d58fbc795/18UMGIM30762.rgb.jpg/100x100bb.jpg", "collectionPrice":19.99, "collectionExplicitness":"notExplicit", "trackCount":29, "copyright":"This Compilation ℗ 2010 Calderstone Productions Limited (a division of Universal Music Group)", "country":"USA", "currency":"USD", "releaseDate":"1973-04-02T08:00:00Z", "primaryGenreName":"Rock"}]
}
"""
    }

    @Test
    fun `verify successful api response returns list of albums`() = runWithDispatcher {
        val mockEngine = buildMockEngine(HttpStatusCode.OK)
        val repository = ITunesAlbumsRepository(ITunesAlbumsAPI(mockEngine))
        val result = repository.fetchAlbums(TEST_QUERY)
        assertThat(result).isNotEmpty()
        assertThat(result[0]).isInstanceOf(Album::class.java)
    }

    @Test
    fun `verify bad api request throws domain exception`() = runWithDispatcher {
        val mockEngine = buildMockEngine(HttpStatusCode.BadRequest)
        val repository = ITunesAlbumsRepository(ITunesAlbumsAPI(mockEngine))
        try {
            repository.fetchAlbums(TEST_QUERY)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(DomainException::class.java)
        }
    }

    private fun buildMockEngine(response: HttpStatusCode): HttpClientEngine {
        return MockEngine { _ ->
            respond(
                content = EXPECTED_RESPONSE,
                status = response,
                headers = headersOf(HttpHeaders.ContentType, "text/javascript")
            )
        }
    }
}