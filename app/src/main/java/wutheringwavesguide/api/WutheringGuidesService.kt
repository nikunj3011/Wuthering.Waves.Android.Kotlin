package wutheringwavesguide.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import wutheringwavesguide.models.api.character.CharacterResponse
import wutheringwavesguide.models.api.characterdetail.CharacterDetailResponse
import wutheringwavesguide.models.api.characterdetails.CharactersDetailsResponse
import wutheringwavesguide.models.api.echo.EchoesResponse
import wutheringwavesguide.models.api.weapon.WeaponResponse

interface WutheringGuidesService {

    @GET("echoes.json")
    suspend fun getEchoes(
    ): Response<EchoesResponse>

    @GET("characters.json")
    suspend fun getCharacters(
    ): Response<CharacterResponse>

    @GET("weapons.json")
    suspend fun getWeapons(
    ): Response<WeaponResponse>

    @GET("charactersdetails.json")
    suspend fun getCharactersDetails(
    ): Response<CharactersDetailsResponse>

    @GET("characters/{id}.json")
    suspend fun getCharactersDetail(
        @Path(value = "id") id: String,
        @Query("articlesCount") count: Int = 0
    ): Response<CharacterDetailResponse>
}