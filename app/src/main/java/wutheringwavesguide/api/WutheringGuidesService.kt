package wutheringwavesguide.api

import retrofit2.Response
import retrofit2.http.GET
import wutheringwavesguide.models.api.character.CharacterResponse
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
}