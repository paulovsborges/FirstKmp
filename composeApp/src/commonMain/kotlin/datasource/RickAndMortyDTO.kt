package datasource

import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyDTO(
    val info: RickAndMortyInfoDTO,
    val results: List<RickAndMortyCharacter>
)

@Serializable
data class RickAndMortyInfoDTO(
    val count: Int
)

@Serializable
data class RickAndMortyCharacter(
    val name: String,
    val status: String,
    val image: String
)
