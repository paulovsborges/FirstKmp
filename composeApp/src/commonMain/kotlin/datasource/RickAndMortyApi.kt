package datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickAndMortyApi {

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api"
    }

    private val client: HttpClient by lazy {
        buildClient()
    }

    suspend fun getCharacters(): Int {
        val response = client.get("$BASE_URL/character").body<RickAndMortyDTO>()
        return response.info.count
    }

    private fun buildClient(): HttpClient {

        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        this.ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}