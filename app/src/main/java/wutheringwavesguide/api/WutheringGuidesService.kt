package wutheringwavesguide.api

import retrofit2.Response
import retrofit2.http.GET
import wutheringwavesguide.models.api.echo.EchoesResponse

interface WutheringGuidesService {

    @GET("echoes.json")
    suspend fun getEchoes(
    ): Response<EchoesResponse>
}