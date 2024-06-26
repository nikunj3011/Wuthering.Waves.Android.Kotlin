package wutheringwavesguide.repository

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import wutheringwavesguide.api.WutheringGuidesService
import wutheringwavesguide.db.CategoryDao
import wutheringwavesguide.db.NewsDatabase
import wutheringwavesguide.models.db.Category
import wutheringwavesguide.util.NetworkBoundResource
import wutheringwavesguide.util.Resource

class HomeRepository(
    private val apiService: WutheringGuidesService
) {
    suspend fun fetchEchoes() = apiService.getEchoes()
    suspend fun fetchCharacters() = apiService.getCharacters()
    suspend fun fetchWeapons() = apiService.getWeapons()
    suspend fun fetchCharactersDetails() = apiService.getCharactersDetails()
}