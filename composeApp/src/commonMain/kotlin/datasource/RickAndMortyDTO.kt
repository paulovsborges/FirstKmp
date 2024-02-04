package datasource

import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyDTO(
    val info: RickAndMortyInfoDTO,
    val results: List<RickAndMortyCharacterDTO>
)

@Serializable
data class RickAndMortyInfoDTO(
    val count: Int
)

@Serializable
data class RickAndMortyCharacterDTO(
    val name: String,
    val status: String,
    val gender: String,
    val origin: RickAndMortyCharacterExtraDTO,
    val location: RickAndMortyCharacterExtraDTO,
    val image: String
)

@Serializable
data class RickAndMortyCharacterExtraDTO(
    val name: String
)
